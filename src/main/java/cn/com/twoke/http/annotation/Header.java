package cn.com.twoke.http.annotation;

import cn.com.twoke.http.utils.SymbolConstant;

import java.lang.annotation.*;

/**
 * <p>请求头配置</p>
 *
 * @author TwoKe
 * @title: Config
 * @projectName http-face
 * @description: 配置
 * @date 2022/5/1118:05
 * @version $Id: $Id
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE})
public @interface Header {

    /**
     * 参数名称
     * @return
     */
    String name();

    /**
     * 配置值
     * @return
     */
    String value() default SymbolConstant.EMPTY;

}
