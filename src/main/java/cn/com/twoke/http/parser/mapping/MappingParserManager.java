package cn.com.twoke.http.parser.mapping;

import cn.com.twoke.http.annotation.mapping.GetMapping;
import cn.com.twoke.http.annotation.mapping.PostMapping;
import cn.com.twoke.http.annotation.mapping.RequestMapping;
import cn.com.twoke.http.type.MethodType;
import cn.com.twoke.kit.annotation.manager.AnnotationManager;
/**
 * <p>映射器解析器</p>
 *
 * @author TwoKe
 * @since 2022/6/6 19:33
 */
public class MappingParserManager {

    public static AnnotationManager<RequestMappingInterface> ANNOTATION_MANAGER = AnnotationManager.build(RequestMappingInterface.class);

    static {
        ANNOTATION_MANAGER.register(RequestMapping.class);
        ANNOTATION_MANAGER.register(GetMapping.class, metadata -> {
            metadata.put("method", MethodType.GET);
            return metadata;
        });
        ANNOTATION_MANAGER.register(PostMapping.class, metadata -> {
            metadata.put("method", MethodType.POST);
            return metadata;
        });
    }


}
