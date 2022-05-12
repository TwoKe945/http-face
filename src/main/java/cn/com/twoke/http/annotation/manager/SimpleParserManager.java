package cn.com.twoke.http.annotation.manager;

import cn.com.twoke.http.annotation.Parser;
import cn.com.twoke.http.annotation.ParserManager;
import cn.com.twoke.http.exp.HttpFaceException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>SimpleParserManager class.</p>
 *
 * @author TwoKe
 * @title: SimpleParserManager
 * @projectName http-face
 * @description: 简单的解析器管理器
 * @date 2022/5/1118:57
 * @version $Id: $Id
 */
public class SimpleParserManager implements ParserManager {

    private List<Parser> parsers = new ArrayList<>();

    /** {@inheritDoc} */
    @Override
    public void addParser(Parser<?> parser) {
        parsers.add(parser);
    }

    /** {@inheritDoc} */
    @Override
    public Object parse(Method method, Class<?> returnClass,Object ...args) {
        for (int i = 0; i < parsers.size(); i++) {
            Annotation instance = method.getAnnotation(parsers.get(i).getType());
            if (instance != null) {
                return parsers.get(i).parse(instance, method, returnClass, args);
            }
        }
        throw new HttpFaceException(method.getName() + ": 需要添加注解 @Get or @Post");
    }
}
