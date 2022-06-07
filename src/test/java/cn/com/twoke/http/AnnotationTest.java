package cn.com.twoke.http;

import cn.com.twoke.http.annotation.Header;
import cn.com.twoke.http.annotation.mapping.GetMapping;
import cn.com.twoke.http.annotation.mapping.PostMapping;
import cn.com.twoke.http.annotation.mapping.RequestMapping;
import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.MethodType;
import cn.com.twoke.http.type.ReturnType;
import cn.com.twoke.kit.annotation.manager.AnnotationManager;
import org.junit.Test;

import java.lang.reflect.*;
/**
 * <p>注解测试类</p>
 *
 * @author TwoKe
 * @since 2022/6/2 20:29
 */
public class AnnotationTest {

    static class AnnotationTestClass {

        @RequestMapping("/user")
        public String test() {
            return "";
        }

    }

    interface ApiAnnotation {
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

    @Test
    public void testInterface() {

        AnnotationManager<ApiAnnotation> annotationManager = AnnotationManager.build(ApiAnnotation.class);
        annotationManager.register(RequestMapping.class);
        annotationManager.register(PostMapping.class, metadata -> {
            metadata.put("method", MethodType.POST);
            return metadata;
        });
        annotationManager.register(GetMapping.class, metadata -> {
            metadata.put("method", MethodType.GET);
            return metadata;
        });


        Method[] declaredMethods = AnnotationTestClass.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            ApiAnnotation apiAnnotation = annotationManager.getAnnotationBy(declaredMethod);
            System.out.println(apiAnnotation.method());
        }

    }

}
