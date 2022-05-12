package cn.com.twoke.http.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <p>ParserManager interface.</p>
 *
 * @author TwoKe
 * @title: RequestContext
 * @projectName http-face
 * @description: 请求管理
 * @date 2022/5/1118:52
 * @version $Id: $Id
 */
public interface ParserManager {

    /**
     * 添加解析器
     *
     * @param parser a {@link cn.com.twoke.http.annotation.Parser} object.
     */
    void addParser(Parser<?> parser);

    /**
     * 解析操作
     *
     * @param method 解析的函数
     * @param args 解析的函数参数
     * @param returnClass a {@link java.lang.Class} object.
     * @return a {@link java.lang.Object} object.
     */
    Object parse(Method method, Class<?> returnClass,Object ...args);

}
