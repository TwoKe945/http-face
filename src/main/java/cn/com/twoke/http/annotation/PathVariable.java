package cn.com.twoke.http.annotation;

import java.lang.annotation.*;

/**
 * <p>路径变量</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 0:20
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface PathVariable {

    /**
     * 参数名称
     * @return
     */
    String name();

}
