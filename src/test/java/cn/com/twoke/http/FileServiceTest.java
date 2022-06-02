package cn.com.twoke.http;

import cn.com.twoke.http.creator.FaceCreator;
import cn.com.twoke.http.entity.Email;
import cn.com.twoke.http.entity.PostFile;
import cn.com.twoke.http.entity.UserInfo;
import cn.com.twoke.http.service.FileService;
import cn.com.twoke.http.service.UserService;
import cn.com.twoke.http.service.XmlService;
import static org.junit.Assert.*;
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
public class FileServiceTest {

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
        UserService userService = FaceCreator.getFace(UserService.class);
        List<UserInfo> userList = userService.getUserList();
        int size = userList.size();
        assertEquals(20, size);
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public void main(String[] args) {



        // UserInfo user = userService.getUserPath("1", "xxx");
        // System.out.println(user);
//        System.out.println(userService.getUserPath("1"));

//        Map<String, Object> save = userService.saveBody(user);
//        System.out.println(save);
//
//
//
//        System.out.println(stringObjectMap);
//


    }




}
