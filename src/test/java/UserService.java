import cn.com.twoke.http.annotation.ConfigItem;
import cn.com.twoke.http.annotation.Param;
import cn.com.twoke.http.annotation.Get;
import cn.com.twoke.http.annotation.Post;
import cn.com.twoke.http.type.ParamPosition;

/**
 * @author TwoKe
 * @title: UserServiceImpl
 * @projectName http-face
 * @description: 用户服务
 * @date 2022/5/1115:22
 */
public interface UserService {


    @Get(
            value = "http://127.0.0.1:4523/mock/673983/one/user/{id}",
            configs = {
                    @ConfigItem(name = "twoke", value = "key")
            }
    )
    String getUserById(@Param(name = "id", position =  ParamPosition.PATH) String id);

    @Post(
        value = "http://localhost:3000/add",
        configs = {
           @ConfigItem(name = "token", value = "test")
        }
    )
    String getUser(@Param(name = "id") String id, @Param(name = "d_token", position = ParamPosition.URL) String token);


}
