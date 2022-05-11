package cn.com.twoke.http.creator;

import cn.com.twoke.http.annotation.ParserManager;
import cn.com.twoke.http.annotation.manager.SimpleParserManager;
import cn.com.twoke.http.annotation.parser.GetParser;
import cn.com.twoke.http.annotation.parser.PostParser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author TwoKe
 * @title: HttpCreator
 * @projectName http-face
 * @description: TODO
 * @date 2022/5/1123:32
 */
public enum HttpCreator {
    INSTANCE;
    private final static ParserManager parserManager = new SimpleParserManager();

    static {
        parserManager.addParser(new GetParser());
        parserManager.addParser(new PostParser());
    }

    public <T> T getBean(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Class<?> returnType = method.getReturnType();
                return parserManager.parse(method, returnType, args);
            }
        });
    }


}
