package cn.com.twoke.http.manager;

import cn.com.twoke.http.config.RequestContext;
import cn.com.twoke.http.parser.ApiParser;
import cn.com.twoke.http.exp.HttpFaceException;
import cn.com.twoke.http.type.MethodType;

import java.util.*;

/**
 * <p>简单的解析器管理器</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 0:50
 */
public class SimpleParserManager implements ParserManager {

    private Map<MethodType,ApiParser> parsers = new HashMap<>();

    /** {@inheritDoc} */
    @Override
    public void register(ApiParser parser) {
        parsers.put(parser.getMethodType(), parser);
    }

    /** {@inheritDoc} */
    @Override
    public Object parse(RequestContext requestContext) {
        ApiParser apiParser = parsers.get(requestContext.getMethod());
        if (Objects.nonNull(apiParser)) {
            System.out.println(requestContext.getMethod() + "：" + requestContext.getUrl());
            return apiParser.parse(requestContext);
        }
        String methodName = requestContext.getFunction().getMethodName();
        throw new HttpFaceException(methodName+ ": 需要添加注解 @Api");
    }
}
