package cn.com.twoke.http.parser.mapping;

import cn.com.twoke.http.annotation.Header;
import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.MethodType;
import cn.com.twoke.http.type.ReturnType;

/**
 * <p>映射器的设备器</p>
 *
 * @author TwoKe
 * @since 2022/6/6 19:30
 */
public interface RequestMappingInterface {

    /**
     * 请求地址
     * @return
     */
    String value();

    /**
     * 请求方法
     * @return
     */
    MethodType method();

    /**
     * 返回的接口格式
     * @return
     */
    ReturnType returnType();

    /**
     * 传递参数类型
     * @return
     */
    ContentType contentType();

    /**
     * 接口固定请求头配置
     * @return
     */
    Header[] headers();

}
