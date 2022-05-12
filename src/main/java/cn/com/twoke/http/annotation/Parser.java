package cn.com.twoke.http.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <p>Parser interface.</p>
 *
 * @author TwoKe
 * @title: Parser
 * @projectName http-face
 * @description: 解析器接口
 * @date 2022/5/1117:36
 * @version $Id: $Id
 */
public interface Parser<T extends Annotation> {

    /**
     * 解析操作
     *
     * @param t 解析的注解
     * @param method 解析的函数
     * @param args 解析的函数参数
     * @param returnClass a {@link java.lang.Class} object.
     * @return a {@link java.lang.Object} object.
     */
    Object parse(T t, Method method, Class<?> returnClass, Object ...args);

    /**
     * 获取解析的类型
     *
     * @return a {@link java.lang.Class} object.
     */
    Class<? extends Annotation> getType();

}
