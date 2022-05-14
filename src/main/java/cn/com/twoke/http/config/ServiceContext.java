package cn.com.twoke.http.config;

import cn.com.twoke.http.annotation.Header;
import cn.com.twoke.http.annotation.ServiceClient;
import cn.com.twoke.http.utils.FnUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * <p>服务上下文</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 0:50
 */
@Data
@Accessors(chain = true)
public class ServiceContext {

    private String base;

    private ParamData<String> headers = ParamData.newParam();

    private ServiceContext() {}
    private ServiceContext(String base, ParamData<String> headers) {
        this.base = base;
        this.headers = headers;
    }

    /**
     * <p>create.</p>
     *
     * @param base a {@link java.lang.String} object.
     * @param headers a {@link cn.com.twoke.http.config.ParamData} object.
     * @return a {@link cn.com.twoke.http.config.ServiceContext} object.
     */
    public static ServiceContext create(String base, ParamData<String> headers){
        return new ServiceContext(base, headers);
    }

    /**
     * <p>create.</p>
     *
     * @return a {@link cn.com.twoke.http.config.ServiceContext} object.
     */
    public static ServiceContext create(){
        return new ServiceContext();
    }

    public static class Builder {

        public static ServiceContext build(ServiceClient service) {
            String base = "";
            ParamData<String> headers = ParamData.newParam();
            if (FnUtil.judge(service, obj -> Objects.nonNull(obj))) {
                if(FnUtil.judge(service.value(), s -> Objects.nonNull(s) && !"".equals(s))){
                    base = service.value();
                }
                Header[] headersAnnotation = service.headers();
                if(FnUtil.judge(headersAnnotation, s -> s.length > 0)) {
                    for (Header header : headersAnnotation) {
                        headers.putData(header.name(), header.value());
                    }
                }
            }
            return ServiceContext.create(base, headers);
        }

    }
}
