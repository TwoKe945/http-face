# http-face

## 简介

> [http-face](https://github.com/TwoKe945/http-face) 是一个HttpClient的增强工具，通过使用注解配置请求，优雅调用外部接口

## 快速开始

### 添加依赖

```xml
<dependency>
  <groupId>cn.com.twoke</groupId>
  <artifactId>http-face</artifactId>
  <version>1.0.6</version>
</dependency>
```
### 准备的实体类

```java
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

@Accessors(chain = true)
public class UserInfo {

    private String id;
    private String realName;
    private String cellPhone;
    private String universityName;
    private String city;
    private String street;

}

@Data
@AllArgsConstructor
public class PostFile {
    private File file;
    private String name;
    private String url;
}

```


## Get、Post 请求
```java
// 设置基础路由
@ServiceClient("http://127.0.0.1:8080")
public interface UserService {
    
    // get请求
    @GetMapping("/userInfo")
    UserInfo getUserInfo(@Param(name = "id") String userId);
    
    // get请求，使用路径传参
    @GetMapping("/userInfo/{id}")
    UserInfo getUserPath(@PathVariable(name ="id") String id);
    
    // 默认为get请求
    @RequestMapping("/user/list")
    List<UserInfo> getUserList();
    
    // post 请求
    @PostMapping(value = "/save", contentType = ContentType.FORM)
    Map<String, Object> saveBody(@Body UserInfo userInfo);

}
```
### 测试

```java
public class Test {

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {

        UserService userService = FaceCreator.getFace(UserService.class);
        System.out.println(userService.getUserList());
        UserInfo user = userService.getUserPath("1");
        System.out.println(user);
        System.out.println(userService.getUserPath("1"));
        Map<String, Object> save = userService.saveBody(user);
        System.out.println(save);
    }

}
```


## 文件上传

```java
@ServiceClient("http://127.0.0.1:8080")
public interface FileService {

    // 文件上传
    @PostMapping(value = "/file", contentType = ContentType.MULTIPART)
    Map<String, Object> saveFile(@Body PostFile file);
    
}
```

### 测试

```java
public class Test {
    
    public static void main(String[] args){
      FileService face = FaceCreator.getFace(FileService.class);
      Map<String, Object> stringObjectMap = face.saveFile(
              new PostFile(
                      new File("D:\\Users\\TwoKe\\Desktop\\px2vwAndvh.txt"),
                      "用户名",
                      "密码"
              )
      );
      System.out.println(stringObjectMap);
    }
}
```


## Xml格式

```java
@ServiceClient("http://localhost:8080")
public interface XmlService {

    @RequestMapping(value = "/xml", returnType = ReturnType.XML)
    Email getXml();

}
```
### 测试

```java
public class Test {
  
  public static void main(String[] args){
    XmlService xmlService =  FaceCreator.getFace(XmlService.class);
    Email xml = xmlService.getXml();
    System.out.println(xml);
  }

}
```
