package cn.com.twoke.http.config;

import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.MethodType;
import cn.com.twoke.http.type.ReturnType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * <p>请求上下文</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 0:34
 */
@Data
@Accessors(chain = true)
public class RequestContext {

    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求头
     */
    private ParamData<String> headers = ParamData.newParam();
    private MethodType method;
    private ContentType contentType;
    private RequestFunction function;
    private ReturnType returnType;

    /**
     * 请求体
     */
    private Object body;

    private RequestContext() {}


    public static class Builder {

        private String base;
        private String suffixUrl;
        private MethodType method;
        private ContentType contentType;
        private RequestFunction function;
        private ReturnType returnType;
        private Object body;
        private ParamData<String> headers = ParamData.<String>newParam();


        public Builder(ServiceContext context) {
            this.base = context.getBase();
            this.headers.putAll(context.getHeaders());
        }

        /**
         * 设置请求地址
         * @param url 请求地址
         * @return 请求上下文 Build
         */
        public Builder url(String url) {
            this.suffixUrl = url;
            return this;
        }

        /**
         * 设置请求方法
         * @param method
         * @return
         */
        public Builder method(MethodType method) {
            this.method = method;
            return this;
        }

        /**
         * 设置请求类型
         * @param contentType
         * @return
         */
        public Builder contentType(ContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        /**
         * 添加请求头
         * @param name 名称
         * @param value 值
         * @return 请求上下文 Build
         */
        public Builder addHeader(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        /**
         * 添加请求头
         * @param headers
         * @return 请求上下文 Build
         */
        public Builder addHeaders(ParamData<String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        /**
         * 请求内容
         * @param body
         * @return
         */
        public Builder body(Object body) {
            this.body = body;
            return this;
        }

        public Builder function(Method function, Object ...args) {
            this.function = new RequestFunction(function, args);
            return this;
        }

        public Builder returnType(ReturnType returnType) {
            this.returnType = returnType;
            return this;
        }



        public RequestContext build() {
            String url = MessageFormat.format("{0}{1}", base, suffixUrl);
            return new RequestContext()
                    .setUrl(url)
                    .setMethod(method)
                    .setBody(body)
                    .setFunction(function)
                    .setContentType(contentType)
                    .setReturnType(returnType)
                    .setHeaders(headers);
        }

    }


}
