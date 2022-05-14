package cn.com.twoke.http.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Arrays;

/**
 * <p>请求的函数信息</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 2:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RequestFunction {

    private Method method;
    private Object[] args;

    /**
     * <p>getMethodName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMethodName() {
        String methodName =  method.getName();
        String returnName = method.getReturnType().getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        StringBuilder params = new StringBuilder();
        Arrays.asList(parameterTypes).forEach(item -> params.append(item.getName() + ","));
        return MessageFormat.format("{0} {1}({2})", returnName, methodName, params.substring(0, params.length()));
    }

    /**
     * <p>getReturnType.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    public Class<?> getReturnType() {
        return method.getReturnType();
    }

}
