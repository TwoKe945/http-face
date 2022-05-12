package cn.com.twoke.http.annotation.parser;

import cn.com.twoke.http.annotation.ConfigItem;
import cn.com.twoke.http.annotation.Parser;
import cn.com.twoke.http.annotation.Post;
import cn.com.twoke.http.annotation.creator.ParamsCreator;
import cn.com.twoke.http.annotation.creator.ReturnCreator;
import cn.com.twoke.http.config.ParamData;
import cn.com.twoke.http.sender.SimpleHttpSender;
import cn.com.twoke.http.type.RequestMethod;
import cn.com.twoke.http.type.ReturnType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <p>PostParser class.</p>
 *
 * @author TwoKe
 * @title: PostRequestParser
 * @projectName http-face
 * @description: Post 解析器
 * @date 2022/5/1117:41
 * @version $Id: $Id
 */
public class PostParser implements Parser<Post> {

    /** {@inheritDoc} */
    @Override
    public Object parse(Post post, Method method, Class<?> returnClass, Object... args) {
//      获取返回类型
        ReturnType returnType = post.returnType();
//      获取请求地址
        String url = post.value();
//      获取参数接口配置
        ConfigItem[] configs = post.configs();
//      获取参数注解
        Annotation[][] annotations = method.getParameterAnnotations();
//      封装请求参数
        ParamData params = ParamData.<Object>newParam();
        ParamData data = ParamData.<Object>newParam();
        ParamData config = ParamData.<String>newParam();
        url = ParamsCreator.build().create(RequestMethod.POST, url, annotations, params, data, args, config);
//      封装请求头配置
        for (ConfigItem configItem : configs) {
            config.putData(configItem.name(), configItem.value());
        }
//      请求数据
        String result = new SimpleHttpSender().doPost(url, params, data, config);
//      处理结果
        return ReturnCreator.build().create(result, returnClass, returnType);
    }

    /** {@inheritDoc} */
    @Override
    public Class<? extends Annotation> getType() {
        return Post.class;
    }

}
