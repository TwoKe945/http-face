package cn.com.twoke.http.annotation;

import cn.com.twoke.http.type.ReturnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author TwoKe
 * @title: Request
 * @projectName http-face
 * @description: 请求
 * @date 2022/5/1115:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Get {
    /**
     * 请求地址
     * @return
     */
    String value();

    /**
     * 返回的接口格式
     * @return
     */
    ReturnType returnType() default ReturnType.JSON;

    /**
     * 接口固定参数配置
     * @return
     */
    ConfigItem[] configs() default {};
}