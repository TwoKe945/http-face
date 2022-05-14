package cn.com.twoke.http.service;

import cn.com.twoke.http.annotation.Api;
import cn.com.twoke.http.annotation.ServiceClient;
import cn.com.twoke.http.entity.Email;
import cn.com.twoke.http.type.ReturnType;

/**
 * <p>TODO</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 10:44
 */
@ServiceClient("http://localhost:8080")
public interface XmlService {

    /**
     * <p>getXml.</p>
     *
     * @return a {@link cn.com.twoke.http.entity.Email} object.
     */
    @Api(value = "/xml", returnType = ReturnType.XML)
    Email getXml();
}
