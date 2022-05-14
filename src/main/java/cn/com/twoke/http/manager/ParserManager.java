package cn.com.twoke.http.manager;

import cn.com.twoke.http.config.RequestContext;
import cn.com.twoke.http.parser.ApiParser;
import cn.com.twoke.http.type.MethodType;

import java.lang.reflect.Method;


/**
 * <p>请求解析管理器</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 0:50
 */
public interface ParserManager {

    /**
     * 添加解析器
     *
     * @param parser a {@link cn.com.twoke.http.parser.ApiParser} object.
     */
    void register(ApiParser parser);

    /**
     * 解析操作
     *
     * @param requestContext a {@link cn.com.twoke.http.config.RequestContext} object.
     * @return a {@link java.lang.Object} object.
     */
    Object parse(RequestContext requestContext);

}
