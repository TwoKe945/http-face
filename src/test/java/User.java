import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TwoKe
 * @title: User
 * @projectName http-face
 * @description: TODO
 * @date 2022/5/1122:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Long age;
    private Role role;
    private String[] items = new String[]{};

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Role{
        private String name;
    }
}
