package cn.com.twoke.http.annotation;

import java.lang.annotation.*;

/**
 * <p>Post数据</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 10:38
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Body {
}
