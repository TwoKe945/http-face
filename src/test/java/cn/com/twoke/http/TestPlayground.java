package cn.com.twoke.http;

import cn.com.twoke.http.creator.FaceCreator;
import cn.com.twoke.http.entity.Email;
import cn.com.twoke.http.entity.PostFile;
import cn.com.twoke.http.entity.UserInfo;
import cn.com.twoke.http.exp.HttpFaceException;
import cn.com.twoke.http.service.FileService;
import cn.com.twoke.http.service.UserService;
import cn.com.twoke.http.service.XmlService;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * <p>Test class.</p>
 *
 * @author TwoKe
 * @version $Id: $Id
 * @since 1.0.2
 */
public class TestPlayground {

    private UserService userService;

    @Before
    public void beforeClass() throws Exception {
        userService = FaceCreator.getFace(UserService.class);
    }

    /**
     * 测试文件上传
     */
    @Test
    public void testUpload() {
       FileService face = FaceCreator.getFace(FileService.class);
       Map<String, Object> stringObjectMap = face.saveFile(
               new PostFile(
                       new File("D:\\Users\\TwoKe\\Desktop\\px2vwAndvh.txt"),
                       "root",
                       "123456"
               )
       );
        assertEquals("{msg=请求成功, code=200, data=px2vwAndvh.txt}", stringObjectMap.toString());
    }

    /**
     * 测试Xml格式转换器
     */
    @Test
    public void testXmlData() {
       XmlService xmlService =  FaceCreator.getFace(XmlService.class);

       Email xml = xmlService.getXml();

       assertEquals("Email(from=TwoKe, to=ZhangSan, content=Hello World)", xml.toString());
    }

    /**
     * 测试get请求
     */
    @Test
    public void testGetMethod() {
        List<UserInfo> userList = userService.getUserList();
        int size = userList.size();
        assertEquals(20, size);
    }

    /**
     * 测试post请求
     */
    @Test
    public void testPostMethod() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId("1").setRealName("TwoKe").setCellPhone("184xxxxxxxxx").setCity("China").setStreet("Cq").setUniversityName("xxxx大学");

        Map<String, Object> result = userService.saveBody(userInfo);
        assertEquals("{msg=请求成功, code=200, data={id=1, realName=TwoKe, cellPhone=184xxxxxxxxx, universityName=xxxx大学, city=China, street=Cq}}", result.toString());
    }

    /**
     * 测试路径参数
     */
    @Test
    public void testPathVariable() {
        userService.getUserPath("1", "123456");
    }

    /**
     * 测试错误请求处理
     */
    @Test
    public void testRequestError() {
        try {
            UserInfo userInfo = userService.getUserPath("01", "123456");
            System.out.println(userInfo);
        } catch (HttpFaceException e) {
            System.out.println("接收到结果处理: " +  e.getResult());
        }
    }
}
