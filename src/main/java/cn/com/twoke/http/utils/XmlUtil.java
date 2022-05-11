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
 * @author TwoKe
 */
@Slf4j
public class XmlUtil {

    public static XmlMapper xmlMapper = new XmlMapper();

    static {
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
    }

    /**
     * 将xml转为bean对象 
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static <T> T xmlToBean(String input, Class<T> cls) throws IOException {
        return xmlMapper.readValue(input, cls);
    }

    /**
     * 将bean转为xml字符串，bean需要配置注解@JacksonXmlProperty等。
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static String beanToXmlStr(Object input) throws IOException {
        String xmlStr = xmlMapper.writeValueAsString(input);
        return xmlStr;
    }

    /**
     * 将bean的xml字符串转为map，bean需要配置注解@JacksonXmlProperty等。
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static Map<String, Object> beanToXmlStrToMap(Object input) throws IOException {
        String xmlStr = xmlMapper.writeValueAsString(input);
        Map<String, Object> map = xmlMapper.readValue(xmlStr, Map.class);
        return map;
    }

    /**
     * 读取文件成XML格式。
     * @param fileName
     * @return
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
     * 字符串xml解析为document
     * @param xml
     * @return
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
     * @Description 字符串输出到XML文件。
     * @return void
     * @Param [str, fileName]
     */
    public static void strToXmlFile(String str, File fileName) throws IOException {
        SAXReader saxReader = new SAXReader();
        org.dom4j.Document document;
        XMLWriter writer = null;
        try {
            document = saxReader.read(new ByteArrayInputStream(str.getBytes("UTF-8")));
            OutputFormat format = OutputFormat.createPrettyPrint();
            /** 将document中的内容写入文件中 */
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