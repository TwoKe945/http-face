package cn.com.twoke.http.service;

import cn.com.twoke.http.entity.PostFile;
import cn.com.twoke.http.annotation.Api;
import cn.com.twoke.http.annotation.Body;
import cn.com.twoke.http.annotation.ServiceClient;
import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.MethodType;

import java.io.File;
import java.util.Map;

/**
 * <p>TODO</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 9:59
 */
@ServiceClient("http://127.0.0.1:8080")
public interface FileService {


    /**
     * <p>saveFile.</p>
     *
     * @param file a {@link cn.com.twoke.http.entity.PostFile} object.
     * @return a {@link java.util.Map} object.
     */
    @Api(value = "/file", method = MethodType.POST, contentType = ContentType.MULTIPART)
    Map<String, Object> saveFile(@Body PostFile file);


}
