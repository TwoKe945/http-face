package cn.com.twoke.http.annotation.manager;

import cn.com.twoke.http.annotation.Parser;
import cn.com.twoke.http.annotation.ParserManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TwoKe
 * @title: SimpleParserManager
 * @projectName http-face
 * @description: TODO
 * @date 2022/5/1118:57
 */
public class SimpleParserManager implements ParserManager {

    private List<Parser> parsers = new ArrayList<>();

    @Override
    public void addParser(Parser<?> parser) {
        parsers.add(parser);
    }

    @Override
    public Object parse(Method method, Class<?> returnClass,Object ...args) {
        for (int i = 0; i < parsers.size(); i++) {
            Annotation instance = method.getAnnotation(parsers.get(i).getType());
            if (instance != null) {
                return parsers.get(i).parse(instance, method, returnClass, args);
            }
        }
        throw new RuntimeException("注解错误！");
    }
}
