package cn.com.twoke.http.creator;

import cn.com.twoke.http.annotation.*;
import cn.com.twoke.http.config.ParamData;
import cn.com.twoke.http.config.RequestContext;
import cn.com.twoke.http.config.ServiceContext;
import cn.com.twoke.http.manager.ParserManager;
import cn.com.twoke.http.manager.SimpleParserManager;
import cn.com.twoke.http.parser.GetParser;
import cn.com.twoke.http.parser.PostParser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.Objects;


/**
 * <p>HttpFace构造器</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 0:50
 */
public class FaceCreator {
    /** Constant <code>parserManager</code> */
    private final static ParserManager parserManager = new SimpleParserManager();

    static {
        /**
         * 注入解析器
         */
        parserManager.register(new GetParser());
        parserManager.register(new PostParser());
    }

    private static  <T> T convert(Object obj) {
        return (T) obj;
    }

    /**
     * <p>获取接口代理.</p>
     *
     * @param clazz a {@link java.lang.Class} object.
     * @param <T> a T object.
     * @return a T object.
     */
    public static  <T> T getFace(Class<T> clazz) {
        // 获取服务注解
        ServiceClient serviceClientAnnotation =  clazz.getDeclaredAnnotation(ServiceClient.class);
        // 服务全局配置
        ServiceContext serviceContext =
                ServiceContext.Builder.build(serviceClientAnnotation);
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, (Object proxy, Method method, Object[] args) -> {
            RequestContext.Builder requestBuilder = new RequestContext.Builder(serviceContext);
            // 获取参数注解
            Annotation[][] parametersAnnotations = method.getParameterAnnotations();
            StringBuilder params = new StringBuilder("?");
//          获取请求
            Api apiAnnotation = method.getAnnotation(Api.class);
            ParamData<String> headers = ParamData.newParam();
            for (Header header : apiAnnotation.headers()) {
                headers.putData(header.name(), header.value());
            }
            String url = apiAnnotation.value();


            for (int i = 0; Objects.nonNull(args) && i < args.length; i++) {
//              参数
                Annotation[] parameterAnnotations = parametersAnnotations[i];

                Object value = args[i];
//
                for (Annotation parameterAnnotation : parameterAnnotations) {
//                    路径变量
                    if (parameterAnnotation instanceof PathVariable) {
                        PathVariable variable = convert(parameterAnnotation);
                        url = url.replace("{"+variable.name()+"}", value.toString());
                    }else if (parameterAnnotation instanceof Header) {
                        Header header = convert(parameterAnnotation);
                        headers.putData(header.name(), header.value());
                    } else if (parameterAnnotation instanceof Param) {
                        Param param = convert(parameterAnnotation);
                        params.append(MessageFormat.format("{0}={1}&", param.name(), value.toString()));
                    } else if (parameterAnnotation instanceof Body){
                        requestBuilder.body(value);
                    }
                }
            }

            // 格式化
            url = params.length() == 1 ? url : url + params.substring(0, params.length() - 1);
            RequestContext requestContext = requestBuilder.url(url)
                    .function(method, args)
                    .addHeaders(headers)
                    .contentType(apiAnnotation.contentType())
                    .method(apiAnnotation.method())
                    .returnType(apiAnnotation.returnType())
                    .build();

            return parserManager.parse(requestContext);
        });
    }



}
