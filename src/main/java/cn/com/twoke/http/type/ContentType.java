package cn.com.twoke.http.type;

import java.text.MessageFormat;

/**
 * <p>内容类型</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/13 23:58
 */
public enum ContentType {
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    TEXT_XML("text/xml"),
    MARKDOWN("text/x-markdown"),
    GIF("image/gif"),
    JPG("image/jpeg"),
    PNG("image/png"),
    JSON("application/json"),
    XML("application/xml"),
    XHTML("application/xhtml+xml"),
    PDF("application/pdf"),
    MSWORD("application/msword"),
    FORM("application/x-www-form-urlencoded"),
    STREAM("application/octet-stream"),
    MULTIPART("multipart/form-data");

    /**
     * <p>Constructor for ContentType.</p>
     *
     * @param type a {@link java.lang.String} object.
     */
    ContentType(String type){
        this.subType = type;
    }

    private String subType;

    /**
     * <p>contentType.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String contentType() {
        return MessageFormat.format("{0}; charset=utf-8", subType);
    }
}
