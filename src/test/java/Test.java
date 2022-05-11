import cn.com.twoke.http.annotation.Get;
import cn.com.twoke.http.annotation.Parser;
import cn.com.twoke.http.annotation.ParserManager;
import cn.com.twoke.http.annotation.Post;
import cn.com.twoke.http.annotation.manager.SimpleParserManager;
import cn.com.twoke.http.annotation.parser.GetParser;
import cn.com.twoke.http.annotation.parser.PostParser;
import cn.com.twoke.http.exp.HttpSenderException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
//        IHttpSender sender = new SimpleHttpSender();
//        String data = sender.doGet("http://127.0.0.1:4523/mock/673983/one/user", ParamData.newParam().putData("id", "1"), null);
//        System.out.println(data);

        ParserManager parserManager = new SimpleParserManager();

        parserManager.addParser(new GetParser());
        parserManager.addParser(new PostParser());

        UserService service = (UserService) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Class<?> returnType = method.getReturnType();
                return parserManager.parse(method, args);
            }
        });

        String userById = service.getUser("1", "动态的token");
        System.out.println(userById);
    }

}
