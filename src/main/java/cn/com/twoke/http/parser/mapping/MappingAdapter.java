package cn.com.twoke.http.parser.mapping;

import cn.com.twoke.http.annotation.Header;
import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.MethodType;
import cn.com.twoke.http.type.ReturnType;

import java.lang.annotation.Annotation;

/**
 * <p>映射器的设备器</p>
 *
 * @author TwoKe
 * @since 2022/6/6 19:30
 */
public interface MappingAdapter<T extends Annotation> {

    /**
     * 请求地址
     * @return
     */
    String getUrl(T t);

    /**
     * 返回的接口格式
     * @return
     */
    ReturnType getReturnType(T t);

    /**
     * 传递参数类型
     * @return
     */
    ContentType getContentType(T t);

    /**
     * 接口固定请求头配置
     * @return
     */
    Header[] getHeaders(T t);

    /**
     * 请求方法
     * @return
     */
    MethodType getMethodType(T t);

}
