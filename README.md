# HTTP-FACE

> 基于dom4j,jackson的http请求注解工具类 


```java
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
    String getUserJson(@Param(name = "id") String id, @Param(name = "d_token", position = ParamPosition.URL) String token);


    @Post(
        value = "http://localhost:3000/add",
        configs = {
           @ConfigItem(name = "token", value = "test")
        }
    )
    User getUserEntity(@Param(name = "id") String id, @Param(name = "d_token", position = ParamPosition.URL) String token);

    @Post(
            value = "http://localhost:3000/add",
            configs = {
                    @ConfigItem(name = "token", value = "test")
            }
    )
    JsonNode getUserNode(@Param(name = "id") String id, @Param(name = "d_token", position = ParamPosition.URL) String token);

    @Get(
        value = "http://localhost:3000/xml",
        returnType = ReturnType.XML
    )
    XmlData getData();

    @Get(
            value = "http://localhost:3000/xml",
            returnType = ReturnType.XML
    )
    Document getDocument();
}
```

xml
```java
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
```
对应的xml

```xml
<Data FullName="John xml">
    <Name name="111">John</Name>
    <Age>30</Age>
    <Id>11</Id>
    <Roles>
      <Role>
        <Name>Admin</Name>
      </Role>
      <Role>
        <Name>Super</Name>
      </Role>
    </Roles>
  </Data>
```



json
```java
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
```

对应的json数据
```json
{
      "id": 12,
      "name": "twoke",
      "age": 21,
      "role": {
        "name": "xxxxx"
      },
      "items": [
        "1",
        "2",
        "3",
        "4"
      ]
    }
```

使用方法
```java
public class Test {



    public static void main(String[] args) {
        UserService service = HttpCreator.INSTANCE.getBean(UserService.class);
        User user = service.getUserEntity("1", "动态的token");
        JsonNode node = service.getUserNode("1", "动态的token");
        System.out.println(user);
        System.out.println(user.getRole().getName());
        System.out.println(service.getData());
        System.out.println(service.getDocument().getRootElement());
    }

}
```
