package cn.com.twoke.http.creator;

import cn.com.twoke.http.annotation.ParserManager;
import cn.com.twoke.http.annotation.manager.SimpleParserManager;
import cn.com.twoke.http.annotation.parser.GetParser;
import cn.com.twoke.http.annotation.parser.PostParser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>HttpCreator class.</p>
 *
 * @author TwoKe
 * @title: HttpCreator
 * @projectName http-face
 * @description: HttpFace构造器
 * @date 2022/5/1123:32
 * @version $Id: $Id
 */
public class FaceCreator {
    /** Constant <code>parserManager</code> */
    private final static ParserManager parserManager = new SimpleParserManager();

    static {
        /**
         * 注入解析器
         */
        parserManager.addParser(new GetParser());
        parserManager.addParser(new PostParser());
    }

    /**
     * <p>获取接口代理.</p>
     *
     * @param clazz a {@link java.lang.Class} object.
     * @param <T> a T object.
     * @return a T object.
     */
    public static  <T> T getFace(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, (Object proxy, Method method, Object[] args) -> {
            Class<?> returnType = method.getReturnType();
            return parserManager.parse(method, returnType, args);
        });
    }


}
