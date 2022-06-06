package cn.com.twoke.http.annotation.mapping;

import cn.com.twoke.http.annotation.Header;
import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.MethodType;
import cn.com.twoke.http.type.ReturnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>请求接口</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 0:20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    /**
     * 请求地址
     * @return
     */
    String value();

    /**
     * 请求方法
     * @return
     */
    MethodType method() default MethodType.GET;

    /**
     * 返回的接口格式
     * @return
     */
    ReturnType returnType() default ReturnType.JSON;

    /**
     * 传递参数类型
     * @return
     */
    ContentType contentType() default ContentType.JSON;

    /**
     * 接口固定请求头配置
     * @return
     */
    Header[] headers() default {};
}
