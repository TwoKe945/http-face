package cn.com.twoke.http.parser;

import cn.com.twoke.http.config.RequestContext;
import cn.com.twoke.http.type.MethodType;

import java.lang.annotation.Annotation;

/**
 * <p>Parser interface.</p>
 *
 * @author TwoKe
 * @version $Id: $Id
 */
public interface ApiParser {

    /**
     * 解析操作
     *
     * @param requestContext a {@link cn.com.twoke.http.config.RequestContext} object.
     * @return a {@link java.lang.Object} object.
     */
    Object parse(RequestContext requestContext);

    /**
     * 方法
     *
     * @return a {@link cn.com.twoke.http.type.MethodType} object.
     */
    MethodType getMethodType();
}
