package cn.com.twoke.http.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Map;

/**
 * <p>XmlUtil class.</p>
 *
 * @author TwoKe
 * @version $Id: $Id
 */
@Slf4j
public class XmlUtil {

    /** Constant <code>xmlMapper</code> */
    public static XmlMapper xmlMapper = new XmlMapper();

    static {
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
    }

    /**
     * <p>xmlToBean.</p>
     *
     * @param input a {@link java.lang.String} object.
     * @param cls a {@link java.lang.Class} object.
     * @param <T> a T object.
     * @return a T object.
     * @throws java.io.IOException if any.
     */
    public static <T> T xmlToBean(String input, Class<T> cls) throws IOException {
        return xmlMapper.readValue(input, cls);
    }


    /**
     * <p>beanToXmlStr.</p>
     *
     * @param input a {@link java.lang.Object} object.
     * @return a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    public static String beanToXmlStr(Object input) throws IOException {
        String xmlStr = xmlMapper.writeValueAsString(input);
        return xmlStr;
    }


    /**
     * <p>beanToXmlStrToMap.</p>
     *
     * @param input a {@link java.lang.Object} object.
     * @return a {@link java.util.Map} object.
     * @throws java.io.IOException if any.
     */
    public static Map<String, Object> beanToXmlStrToMap(Object input) throws IOException {
        String xmlStr = xmlMapper.writeValueAsString(input);
        Map<String, Object> map = xmlMapper.readValue(xmlStr, Map.class);
        return map;
    }

    /**
     * <p>xmlFileToString.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String xmlFileToString(String fileName) {
        try {
            //新建一个解析类
            SAXReader saxReader = new SAXReader();
            //读入一个文件
            Document tempDocument = saxReader.read(fileName);
            return tempDocument.asXML();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>xmlToDocument.</p>
     *
     * @param xml a {@link java.lang.String} object.
     * @return a {@link org.dom4j.Document} object.
     */
    public static Document xmlToDocument(String xml) {
        try {
            //新建一个解析类
            SAXReader saxReader = new SAXReader();
            InputStream in =new ByteArrayInputStream(xml.getBytes());
            return saxReader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * <p>strToXmlFile.</p>
     *
     * @param str a {@link java.lang.String} object.
     * @param fileName a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public static void strToXmlFile(String str, File fileName) throws IOException {
        SAXReader saxReader = new SAXReader();
        org.dom4j.Document document;
        XMLWriter writer = null;
        try {
            document = saxReader.read(new ByteArrayInputStream(str.getBytes("UTF-8")));
            OutputFormat format = OutputFormat.createPrettyPrint();
            writer = new XMLWriter(new FileWriter(fileName), format);
            writer.write(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }
}
