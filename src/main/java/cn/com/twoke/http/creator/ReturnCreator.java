package cn.com.twoke.http.creator;

import cn.com.twoke.http.exp.HttpFaceException;
import cn.com.twoke.http.type.ReturnType;
import cn.com.twoke.http.utils.ObjectMapperUtil;
import cn.com.twoke.http.utils.XmlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dom4j.Document;

import java.io.IOException;

/**
 * <p>结果创造器</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 0:50
 */
public final class ReturnCreator {

    private ReturnCreator() {}

    /**
     * <p>build.</p>
     *
     * @return a {@link cn.com.twoke.http.creator.ReturnCreator} object.
     */
    public static ReturnCreator build() { return new ReturnCreator(); }

    /**
     * <p>create.</p>
     *
     * @param result a {@link java.lang.String} object.
     * @param returnClass a {@link java.lang.Class} object.
     * @param returnType a {@link cn.com.twoke.http.type.ReturnType} object.
     * @return a {@link java.lang.Object} object.
     */
    public Object create(String result, Class<?> returnClass, ReturnType returnType) {
//      如果是String 对象直接返回
        if (returnClass.equals(String.class)) {
            return result;
        }
        if (ReturnType.JSON.equals(returnType) ){
            return parseJson(result, returnClass);
        }
        if (ReturnType.XML.equals(returnType) ){
            return parseXml(result, returnClass);
        }
        return null;
    }

    private Object parseJson(String result, Class<?> returnClass) {
        try {
            if (returnClass.equals(JsonNode.class)) {
                return ObjectMapperUtil.getInstance().readTree(result);
            }
            return ObjectMapperUtil.getInstance().readValue(result, returnClass);
        } catch (JsonProcessingException e) {
            throw new HttpFaceException("请求结果转化失败！").setResult(result);
        }
    }

    private Object parseXml(String result, Class<?> returnClass) {
        try {
            if (returnClass.equals(Document.class)) {
                return XmlUtil.xmlToDocument(result);
            }
            return XmlUtil.xmlToBean(result, returnClass);
        } catch (IOException e) {
            throw new HttpFaceException("请求结果转化失败！").setResult(result);
        }
    }
}
