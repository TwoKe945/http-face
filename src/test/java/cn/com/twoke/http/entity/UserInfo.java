package cn.com.twoke.http.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
/**
 * <p>UserInfo class.</p>
 *
 * @author TwoKe
 * @version $Id: $Id
 * @since 1.0.2
 */
@Accessors(chain = true)
public class UserInfo {

    private String id;

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机
     */
    private String cellPhone;
    /**
     * 大学
     */
    private String universityName;
    /**
     * 城市
     */
    private String city;
    /**
     * 地址
     */
    private String street;

}
