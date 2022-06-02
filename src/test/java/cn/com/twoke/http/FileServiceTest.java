package cn.com.twoke.http;

import cn.com.twoke.http.creator.FaceCreator;
import cn.com.twoke.http.entity.PostFile;
import cn.com.twoke.http.entity.UserInfo;
import cn.com.twoke.http.service.FileService;
import cn.com.twoke.http.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
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
        Assert.assertEquals("{msg=请求成功, code=200, data=px2vwAndvh.txt}", stringObjectMap.toString());
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {

        UserService userService = FaceCreator.getFace(UserService.class);

        System.out.println(userService.getUserList());

        UserInfo user = userService.getUserPath("1", "xxx");
        System.out.println(user);
//        System.out.println(userService.getUserPath("1"));

//        Map<String, Object> save = userService.saveBody(user);
//        System.out.println(save);
//
//
//
//        System.out.println(stringObjectMap);
//
//        XmlService xmlService =  FaceCreator.getFace(XmlService.class);
//
//        Email xml = xmlService.getXml();
//
//        System.out.println(xml);

    }




}
