package cn.com.twoke.http.service;
import cn.com.twoke.http.entity.UserInfo;
import cn.com.twoke.http.annotation.*;
import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.MethodType;

import java.util.List;
import java.util.Map;


/**
 * <p>UserService interface.</p>
 *
 * @author TwoKe
 * @version $Id: $Id
 * @since 1.0.2
 */
@ServiceClient("http://127.0.0.1:8080")
public interface UserService {

    /**
     * <p>getUserInfo.</p>
     *
     * @param userId a {@link java.lang.String} object.
     * @return a {@link cn.com.twoke.http.entity.UserInfo} object.
     */
    @Api("/userInfo")
    UserInfo getUserInfo(@Param(name = "id") String userId);

    /**
     * <p>getUserPath.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @return a {@link cn.com.twoke.http.entity.UserInfo} object.
     */
    @Api("/userInfo/{id}")
    UserInfo getUserPath(@PathVariable(name ="id") String id);

    /**
     * <p>getUserList.</p>
     *
     * @return a {@link java.util.List} object.
     */
    @Api("/user/list")
    List<UserInfo> getUserList();

    /**
     * <p>saveBody.</p>
     *
     * @param userInfo a {@link cn.com.twoke.http.entity.UserInfo} object.
     * @return a {@link java.util.Map} object.
     */
    @Api(value = "/save", method = MethodType.POST, contentType = ContentType.FORM)
    Map<String, Object> saveBody(@Body UserInfo userInfo);

}
