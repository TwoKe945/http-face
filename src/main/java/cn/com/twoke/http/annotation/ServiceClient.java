package cn.com.twoke.http.annotation;

import cn.com.twoke.http.utils.SymbolConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>服务客户端</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/13 17:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ServiceClient {

    /**
     * 基础路径
     * @return
     */
    String value() default SymbolConstant.EMPTY;


    /**
     * 全局静态的头文件配置
     * @return
     */
    Header[] headers() default {};

}
