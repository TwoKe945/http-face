package cn.com.twoke.http.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author TwoKe
 * @title: RequestContext
 * @projectName http-face
 * @description: 请求管理
 * @date 2022/5/1118:52
 */
public interface ParserManager {

    /**
     * 添加解析器
     * @param parser
     */
    void addParser(Parser<?> parser);

    /**
     * 解析操作
     * @param method 解析的函数
     * @param args 解析的函数参数
     * @return
     */
    Object parse(Method method, Object ...args);

}
