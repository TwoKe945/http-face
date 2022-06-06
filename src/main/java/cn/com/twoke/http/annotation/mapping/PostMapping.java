package cn.com.twoke.http.annotation.mapping;

import cn.com.twoke.http.annotation.Header;
import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.ReturnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Post 请求封装</p>
 *
 * @author TwoKe
 * @since 2022/6/6 19:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PostMapping {

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
