package cn.com.twoke.http.annotation.parser;

import cn.com.twoke.http.annotation.*;
import cn.com.twoke.http.annotation.creator.ParamsCreator;
import cn.com.twoke.http.annotation.creator.ReturnCreator;
import cn.com.twoke.http.config.ParamData;
import cn.com.twoke.http.sender.SimpleHttpSender;
import cn.com.twoke.http.type.RequestMethod;
import cn.com.twoke.http.type.ReturnType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <p>GetParser class.</p>
 *
 * @author TwoKe
 * @title: GetRequestParser
 * @projectName http-face
 * @description: Get 解析器
 * @date 2022/5/1117:35
 * @version $Id: $Id
 */
public class GetParser implements Parser<Get> {

    /** {@inheritDoc} */
    @Override
    public Object parse(Get get, Method method, Class<?> returnClass, Object... args) {
//      获取返回类型
        ReturnType returnType = get.returnType();
//      获取参数接口配置
        ConfigItem[] configs = get.configs();
//      获取请求地址
        String url = get.value();
//      获取参数注解
        Annotation[][] annotations = method.getParameterAnnotations();
//      封装请求参数
        ParamData params = ParamData.<Object>newParam();
        ParamData data = ParamData.<Object>newParam();
        ParamData config = ParamData.<String>newParam();
        url = ParamsCreator.build().create(RequestMethod.GET, url, annotations, params, data, args, config);
//      封装请求头配置
        for (ConfigItem configItem : configs) {
            config.putData(configItem.name(), configItem.value());
        }
//      请求结果
        String result = new SimpleHttpSender().doGet(url, params, config);
        return ReturnCreator.build().create(result, returnClass, returnType);
    }


    /** {@inheritDoc} */
    @Override
    public Class<? extends Annotation> getType() {
        return Get.class;
    }

}
