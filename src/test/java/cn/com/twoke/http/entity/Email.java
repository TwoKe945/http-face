package cn.com.twoke.http.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * <p>邮箱信息</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 10:42
 */
@Data
@JacksonXmlRootElement(localName = "Email")
public class Email {
    @JacksonXmlProperty(localName = "From")
    private String from;
    @JacksonXmlProperty(localName = "To")
    private String to;
    @JacksonXmlProperty(localName = "Content")
    private String content;
}
