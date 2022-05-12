package cn.com.twoke.http;

import cn.com.twoke.http.creator.HttpCreator;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author TwoKe
 * @title: Test
 * @projectName http-face
 * @description: TODO
 * @date 2022/5/1113:28
 */
public class Test {



    public static void main(String[] args) {
        UserService service = HttpCreator.getFace(UserService.class);
        User user = service.getUserEntity("1", "动态的token");
        JsonNode node = service.getUserNode("1", "动态的token");
        System.out.println(user);
        System.out.println(user.getRole().getName());
        System.out.println(service.getData());
        System.out.println(service.getDocument().getRootElement());
    }

}
