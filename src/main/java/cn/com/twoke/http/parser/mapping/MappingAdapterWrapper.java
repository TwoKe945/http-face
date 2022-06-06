package cn.com.twoke.http.parser.mapping;

import cn.com.twoke.http.annotation.Header;
import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.MethodType;
import cn.com.twoke.http.type.ReturnType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * <p>TODO</p>
 *
 * @author TwoKe
 * @since 2022/6/6 20:03
 */
@Data
@Builder
@Accessors(chain = true)
public class MappingAdapterWrapper<T extends Annotation> {

    private T annotation;
    private MappingAdapter<T> adapter;


    /**
     * 请求地址
     * @return
     */
    public String value() {
        return adapter.getUrl(annotation);
    }

    /**
     * 请求方法
     * @return
     */
    public MethodType method() {
        return adapter.getMethodType(annotation);
    }

    /**
     * 返回的接口格式
     * @return
     */
    public ReturnType returnType() {
        return adapter.getReturnType(annotation);
    }

    /**
     * 传递参数类型
     * @return
     */
    public ContentType contentType() {
        return adapter.getContentType(annotation);
    }

    /**
     * 接口固定请求头配置
     * @return
     */
    public Header[] headers() {
        return adapter.getHeaders(annotation);
    }

}
