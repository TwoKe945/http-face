package cn.com.twoke.http;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

/**
 * @author TwoKe
 * @title: Data
 * @projectName http-face
 * @description: TODO
 * @date 2022/5/1122:48
 */
@Data
@JacksonXmlRootElement(localName = "Data")
public class XmlData {
    @JacksonXmlProperty(localName = "Id")
    private Long id;
    @JacksonXmlElementWrapper(localName = "Name", useWrapping = false)
    private Name name;
    @JacksonXmlProperty(localName = "Age")
    private Long age;
    @JacksonXmlProperty(localName = "FullName", isAttribute = true)
    private String fullName;
    @JacksonXmlElementWrapper(localName = "Roles")
    private List<Role> roles;

    @Data
    @JacksonXmlRootElement(localName = "Role")
    public static class Role {
        @JacksonXmlProperty(localName = "Name")
        private String name;
    }

    @Data
    public static class Name {
        @JacksonXmlProperty(localName = "name", isAttribute = true)
        private String name;
    }
}
