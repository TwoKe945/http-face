package cn.com.twoke.http.annotation;

import java.lang.annotation.*;

/**
 * <p>Get 请求参数</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 3:40
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Param {
    /**
     * 参数名称
     * @return
     */
    String name();

}
