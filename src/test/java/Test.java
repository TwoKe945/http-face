import cn.com.twoke.http.annotation.Get;
import cn.com.twoke.http.annotation.Parser;
import cn.com.twoke.http.annotation.ParserManager;
import cn.com.twoke.http.annotation.Post;
import cn.com.twoke.http.annotation.manager.SimpleParserManager;
import cn.com.twoke.http.annotation.parser.GetParser;
import cn.com.twoke.http.annotation.parser.PostParser;
import cn.com.twoke.http.creator.HttpCreator;
import cn.com.twoke.http.exp.HttpSenderException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Objects;

/**
 * @author TwoKe
 * @title: Test
 * @projectName http-face
 * @description: TODO
 * @date 2022/5/1113:28
 */
public class Test {



    public static void main(String[] args) {
        UserService service = HttpCreator.INSTANCE.getBean(UserService.class);
        User user = service.getUserEntity("1", "动态的token");
        JsonNode node = service.getUserNode("1", "动态的token");
        System.out.println(user);
        System.out.println(user.getRole().getName());
        System.out.println(service.getData());
        System.out.println(service.getDocument().getRootElement());
    }

}
