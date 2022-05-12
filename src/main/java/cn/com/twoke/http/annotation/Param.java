package cn.com.twoke.http.annotation;

import cn.com.twoke.http.type.ParamPosition;

import java.lang.annotation.*;

/**
 * <p>Param class.</p>
 *
 * @author TwoKe
 * @title: Param
 * @projectName http-face
 * @description: TODO
 * @date 2022/5/1115:24
 * @version $Id: $Id
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface  Param {

    /**
     * 参数名
     * @return
     */
    String name();

    /**
     * 位置，默认是key1=value1
     * @return
     */
    ParamPosition position() default ParamPosition.DEFAULT;

}
