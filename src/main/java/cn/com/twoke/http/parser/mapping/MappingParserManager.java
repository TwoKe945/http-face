package cn.com.twoke.http.parser.mapping;

import cn.com.twoke.http.annotation.Header;
import cn.com.twoke.http.annotation.mapping.GetMapping;
import cn.com.twoke.http.annotation.mapping.PostMapping;
import cn.com.twoke.http.annotation.mapping.RequestMapping;
import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.MethodType;
import cn.com.twoke.http.type.ReturnType;
import lombok.Builder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>映射器解析器</p>
 *
 * @author TwoKe
 * @since 2022/6/6 19:33
 */
@Builder
public class MappingParserManager {

    public Map<Class<? extends Annotation>, MappingAdapter> registerPool;

    public <T extends Annotation> MappingParserManager register(Class<T> clazz, MappingAdapter<T> adapter) {
        registerPool.put(clazz, adapter);
        return this;
    }

    public MappingAdapterWrapper getMapping(Method method) {
        AtomicReference<MappingAdapterWrapper> mappingAdapter = new AtomicReference<>();
        registerPool.keySet().forEach(item -> {
            Annotation annotation = method.getDeclaredAnnotation(item);
            if (Objects.nonNull(annotation)) {
                MappingAdapterWrapper mappingAdapterWrapper = MappingAdapterWrapper.builder()
                        .adapter(registerPool.get(item))
                        .annotation(annotation)
                        .build();
                mappingAdapter.set(mappingAdapterWrapper);
                return;
            }
        });

        if (Objects.nonNull(mappingAdapter.get())) {
            return mappingAdapter.get();
        }
        throw new RuntimeException("error");
    }


    public static MappingParserManager MappingInstance = MappingParserManager.builder()
            .registerPool(new HashMap<>()).build();

    static {
        /**
         * 兼容配置
         */
        MappingInstance.register(RequestMapping.class, new MappingAdapter<RequestMapping>() {
            @Override
            public String getUrl(RequestMapping requestMapping) {
                return requestMapping.value();
            }

            @Override
            public ReturnType getReturnType(RequestMapping requestMapping) {
                return requestMapping.returnType();
            }

            @Override
            public ContentType getContentType(RequestMapping requestMapping) {
                return requestMapping.contentType();
            }

            @Override
            public Header[] getHeaders(RequestMapping requestMapping) {
                return requestMapping.headers();
            }

            @Override
            public MethodType getMethodType(RequestMapping requestMapping) {
                return requestMapping.method();
            }
        });
        /**
         * Get请求
         */
        MappingInstance.register(GetMapping.class, new MappingAdapter<GetMapping>() {

            @Override
            public String getUrl(GetMapping getMapping) {
                return getMapping.value();
            }

            @Override
            public ReturnType getReturnType(GetMapping getMapping) {
                return getMapping.returnType();
            }

            @Override
            public ContentType getContentType(GetMapping getMapping) {
                return getMapping.contentType();
            }

            @Override
            public Header[] getHeaders(GetMapping getMapping) {
                return getMapping.headers();
            }

            @Override
            public MethodType getMethodType(GetMapping getMapping) {
                return MethodType.GET;
            }
        });
        /**
         * Post请求
         */
        MappingInstance.register(PostMapping.class, new MappingAdapter<PostMapping>() {

            @Override
            public String getUrl(PostMapping postMapping) {
                return postMapping.value();
            }

            @Override
            public ReturnType getReturnType(PostMapping postMapping) {
                return postMapping.returnType();
            }

            @Override
            public ContentType getContentType(PostMapping postMapping) {
                return postMapping.contentType();
            }

            @Override
            public Header[] getHeaders(PostMapping postMapping) {
                return postMapping.headers();
            }

            @Override
            public MethodType getMethodType(PostMapping postMapping) {
                return MethodType.POST;
            }
        });
    }

}
