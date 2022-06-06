package cn.com.twoke.http;

import cn.com.twoke.http.annotation.mapping.RequestMapping;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

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

    @Test
    public void testInterface() {

        Method[] declaredMethods = AnnotationTestClass.class.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {

            Annotation annotation = declaredMethod.getAnnotation(RequestMapping.class);
            System.out.println(annotation);
        }


    }

}
