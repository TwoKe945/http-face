package cn.com.twoke.http.annotation.creator;

import cn.com.twoke.http.annotation.Param;
import cn.com.twoke.http.config.ParamData;
import cn.com.twoke.http.exp.HttpFaceException;
import cn.com.twoke.http.type.ParamPosition;
import cn.com.twoke.http.type.RequestMethod;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * <p>ParamsCreator class.</p>
 *
 * @author TwoKe
 * @title: ParamsCreator
 * @projectName http-face
 * @description: 参数构建器
 * @date 2022/5/1118:12
 * @version $Id: $Id
 */
public final   class ParamsCreator {

    private ParamsCreator() {}

    /**
     * <p>build.</p>
     *
     * @return a {@link cn.com.twoke.http.annotation.creator.ParamsCreator} object.
     */
    public static ParamsCreator build() {
        return new ParamsCreator();
    }

    /**
     * <p>create.</p>
     *
     * @param url 请求地址
     * @param parameterAnnotations 参数注解
     * @param params 请求路径参数
     * @param data 请求体参数
     * @param args 请求参数值
     * @param config 请求头参数
     * @param method a {@link cn.com.twoke.http.type.RequestMethod} object.
     * @return a {@link java.lang.String} object.
     */
    public String create(
            RequestMethod method,
            String url,
            Annotation[][] parameterAnnotations,
            ParamData params,
            ParamData data,
            Object[] args,
            ParamData<String> config) {
        for (int i = 0; Objects.nonNull(args) && i < args.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            Object value = args[i];
            if (annotations.length != 1) {
                throw new HttpFaceException("Request");
            }
            Annotation annotation = annotations[0];
            if(annotation instanceof Param) {
                Param paramAnnotation= (Param) annotation;
                // 获取参数名
                String name = paramAnnotation.name();
                // 获取参数的位置
                ParamPosition position = paramAnnotation.position();

                switch (position) {
                    case PATH:
                        String pathKey = "{"+name+"}";
                        if (url.contains(pathKey)) {
                            url = url.replace(pathKey, value.toString());;
                        } else {
                            throw new HttpFaceException(url + " 不包含地址参数:" + pathKey);
                        }
                        break;
                    case HEADER:
                        config.putData(name, value.toString());
                        break;
                    case URL:
                        params.putData(name, value);
                        break;
                    case BODY:
                        data.putData(name, value);
                        break;
                    case DEFAULT:
                        dispatchDefault(method, params, data, value, name);
                        break;
                }
            }
        }
        return url;
    }

    /**
     * 调度配置默认的格式要求
     * @param method
     * @param params
     * @param data
     * @param value
     * @param name
     */
    private void dispatchDefault(RequestMethod method, ParamData params, ParamData data, Object value, String name) {
        if (RequestMethod.GET.equals(method)) {
            params.putData(name, value);
        } else if(RequestMethod.POST.equals(method)) {
            data.putData(name, value);
        }
    }
}
