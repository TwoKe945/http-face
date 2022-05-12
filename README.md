# http-face

## 简介

> [http-face](https://github.com/TwoKe945/http-face) 是一个HttpClient的增强工具，通过使用注解配置请求，优雅调用外部接口

## 快速开始

### 添加依赖

```xml
<dependency>
  <groupId>cn.com.twoke</groupId>
  <artifactId>http-face</artifactId>
  <version>1.0.0</version>
</dependency>
```



### 创建外部调用接口

```java
/**
     * 定义外部调用接口
     */
interface UserService {
  /**
         * 默认是解析json, bean文件需字段对应
         * 方法：get
         * 地址：http://localhost:8080/user/list
         * @return
         */
  @Get("http://localhost:8080/user/list")
  List<UserInfo> findList();

  /**
         默认是解析json, bean文件需字段对应
         * 方法：get
         * 地址：http://localhost:8080/userInfo
         * @param id 参数绑定 ?id=xxx
         * @return
         */
  @Get("http://localhost:8080/userInfo")
  UserInfo findUserById(@Param(name = "id") String id);

  /**
         默认是解析json, bean文件需字段对应
         * 方法：get
         * 地址：http://localhost:8080/userInfo/{id}
         * @param id 参数绑定在path
         * @return
         */
  @Get("http://localhost:8080/userInfo/{id}")
  UserInfo findUserByIdOnPath(@Param(name = "id", position = ParamPosition.PATH) String id);

  /**
         * 默认是解析json, bean文件需字段对应
         * 方法：get
         * 地址：http://localhost:8080/auth_current
         * @param token 参数绑定在header （动态参数）
         * @return
         */
  @Get("http://localhost:8080/auth_current")
  UserInfo findUserByTokenOnHeader(@Param(name = "token", position = ParamPosition.HEADER) String token);

  /**
         * 默认是解析json, bean文件需字段对应
         * 方法：get
         * 地址：http://localhost:8080/auth_current
         * 参数绑定在header （静态参数）
         * @return
         */
  @Get(value = "http://localhost:8080/auth_current", configs = {
    @ConfigItem(name = "token", value = "xx12e12dewcdrefvrev")
  })
  UserInfo findUserByTokenOnHeader();

  /**
         * 解析xml
         * 方法：get
         * 地址：http://localhost:8080/xml
         * @return
         */
  @Get(value = "http://localhost:8080/xml", returnType = ReturnType.XML)
  Email email();
}

```

### 准备实体对象

#### 邮件

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
```

对应xml

```xml
<Email>
	<From>lisi</From>" +
  <To>zhangsan</To>
  <Content> this is content </Content>
</Email>
```

### 用户信息

```java
@Data
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
```

对应json

```
{
	id: 'xxx',
	realName: 'xxxx',
	cellPhone: 'xxxx',
	universityName: 'xxxx',
	city: 'xxx',
	street: 'xxxx'
}
```

### 测试请求

```java
public class Test {
  
  public static void main(String[] args) {
     UserService bean = HttpCreator.getFace(UserService.class);

        List<UserInfo> list = bean.findList();
        System.out.println(list);

        log.info("查询所有用户信息 => {}", list);

        try {
            log.info("传递path id查询用户信息 => {}", bean.findUserById("432-87-8071"));
        }catch (Exception e) {
            log.error("Id信息不存在");
        }

        try {
            log.info("传递path id查询用户信息 => {}", bean.findUserByIdOnPath("432-87-8071"));
        }catch (Exception e) {
            log.error("Id信息不存在");
        }

        log.info("传递header path查询用户信息 => {}", bean.findUserByTokenOnHeader("123456"));

        log.info("传递header path查询用户信息 => {}", bean.findUserByTokenOnHeader("12345111"));

        log.info("xml查询email信息 => {}", bean.email());
  }
  
}
```

#### 结果

```sh
2022-05-12 13:03:54.634  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : doGet - http://localhost:8080/user/list
2022-05-12 13:03:54.654  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : recv - [{"id":"138-32-6092","realName":"洪昊焱","cellPhone":"14513625571","universityName":"北艺术大学","city":"滨州","street":"邵侬0号"},{"id":"705-93-3169","realName":"丁越泽","cellPhone":"13735227083","universityName":"东北农业大学","city":"胶南","street":"梁桥755号"},{"id":"799-02-0148","realName":"龚文轩","cellPhone":"15785650527","universityName":"北经贸大学","city":"唐山","street":"阎巷49201号"},{"id":"286-94-4589","realName":"张智渊","cellPhone":"17184101643","universityName":"中国农业大学","city":"本溪","street":"吴桥65589号"},{"id":"267-54-1474","realName":"吴文轩","cellPhone":"13026350677","universityName":"东北体育大学","city":"大连","street":"蒋旁6号"},{"id":"728-22-5917","realName":"张健柏","cellPhone":"15806349158","universityName":"东北艺术大学","city":"张家界","street":"彭栋63号"},{"id":"370-35-2072","realName":"薛智渊","cellPhone":"15728469717","universityName":"西科技大学","city":"阳泉","street":"谢巷4号"},{"id":"318-44-1215","realName":"于子轩","cellPhone":"15335304204","universityName":"北理工大学","city":"大庆","street":"潘栋248号"},{"id":"411-50-5126","realName":"邓绍齐","cellPhone":"17192233408","universityName":"东北体育大学","city":"昆山","street":"阎街0502号"},{"id":"043-84-7711","realName":"袁熠彤","cellPhone":"15193803407","universityName":"西南大学","city":"安阳","street":"孔桥817号"},{"id":"684-28-4959","realName":"魏子涵","cellPhone":"17562408960","universityName":"西经贸大学","city":"芜湖","street":"孔路791号"},{"id":"153-43-1918","realName":"白昊焱","cellPhone":"17158657861","universityName":"南体育大学","city":"岳阳","street":"谢街0号"},{"id":"650-23-4822","realName":"石弘文","cellPhone":"17728325193","universityName":"西南艺术大学","city":"曲靖","street":"雷旁6957号"},{"id":"785-23-0911","realName":"洪雪松","cellPhone":"15012225810","universityName":"东北大学","city":"曲靖","street":"戴桥7号"},{"id":"674-94-4905","realName":"田潇然","cellPhone":"17580294958","universityName":"北农业大学","city":"盐城","street":"刘侬18706号"},{"id":"864-30-6912","realName":"韩楷瑞","cellPhone":"15673737519","universityName":"南经贸大学","city":"芜湖","street":"高栋791号"},{"id":"247-71-7206","realName":"王明","cellPhone":"17165474302","universityName":"北农业大学","city":"吉林","street":"周巷21号"},{"id":"693-16-3342","realName":"薛立辉","cellPhone":"18279159343","universityName":"北大学","city":"太原","street":"杜中心087号"},{"id":"222-47-4051","realName":"石智渊","cellPhone":"17313032523","universityName":"东南科技大学","city":"绵阳","street":"阎桥66787号"},{"id":"430-14-8252","realName":"阎哲瀚","cellPhone":"17070126553","universityName":"中国科技大学","city":"舟山","street":"孟栋88号"}]
[{id=138-32-6092, realName=洪昊焱, cellPhone=14513625571, universityName=北艺术大学, city=滨州, street=邵侬0号}, {id=705-93-3169, realName=丁越泽, cellPhone=13735227083, universityName=东北农业大学, city=胶南, street=梁桥755号}, {id=799-02-0148, realName=龚文轩, cellPhone=15785650527, universityName=北经贸大学, city=唐山, street=阎巷49201号}, {id=286-94-4589, realName=张智渊, cellPhone=17184101643, universityName=中国农业大学, city=本溪, street=吴桥65589号}, {id=267-54-1474, realName=吴文轩, cellPhone=13026350677, universityName=东北体育大学, city=大连, street=蒋旁6号}, {id=728-22-5917, realName=张健柏, cellPhone=15806349158, universityName=东北艺术大学, city=张家界, street=彭栋63号}, {id=370-35-2072, realName=薛智渊, cellPhone=15728469717, universityName=西科技大学, city=阳泉, street=谢巷4号}, {id=318-44-1215, realName=于子轩, cellPhone=15335304204, universityName=北理工大学, city=大庆, street=潘栋248号}, {id=411-50-5126, realName=邓绍齐, cellPhone=17192233408, universityName=东北体育大学, city=昆山, street=阎街0502号}, {id=043-84-7711, realName=袁熠彤, cellPhone=15193803407, universityName=西南大学, city=安阳, street=孔桥817号}, {id=684-28-4959, realName=魏子涵, cellPhone=17562408960, universityName=西经贸大学, city=芜湖, street=孔路791号}, {id=153-43-1918, realName=白昊焱, cellPhone=17158657861, universityName=南体育大学, city=岳阳, street=谢街0号}, {id=650-23-4822, realName=石弘文, cellPhone=17728325193, universityName=西南艺术大学, city=曲靖, street=雷旁6957号}, {id=785-23-0911, realName=洪雪松, cellPhone=15012225810, universityName=东北大学, city=曲靖, street=戴桥7号}, {id=674-94-4905, realName=田潇然, cellPhone=17580294958, universityName=北农业大学, city=盐城, street=刘侬18706号}, {id=864-30-6912, realName=韩楷瑞, cellPhone=15673737519, universityName=南经贸大学, city=芜湖, street=高栋791号}, {id=247-71-7206, realName=王明, cellPhone=17165474302, universityName=北农业大学, city=吉林, street=周巷21号}, {id=693-16-3342, realName=薛立辉, cellPhone=18279159343, universityName=北大学, city=太原, street=杜中心087号}, {id=222-47-4051, realName=石智渊, cellPhone=17313032523, universityName=东南科技大学, city=绵阳, street=阎桥66787号}, {id=430-14-8252, realName=阎哲瀚, cellPhone=17070126553, universityName=中国科技大学, city=舟山, street=孟栋88号}]
2022-05-12 13:03:54.703  INFO 17400 --- [           main] c.c.t.h.HttpFaceDemoApplicationTests     : 查询所有用户信息 => [{id=138-32-6092, realName=洪昊焱, cellPhone=14513625571, universityName=北艺术大学, city=滨州, street=邵侬0号}, {id=705-93-3169, realName=丁越泽, cellPhone=13735227083, universityName=东北农业大学, city=胶南, street=梁桥755号}, {id=799-02-0148, realName=龚文轩, cellPhone=15785650527, universityName=北经贸大学, city=唐山, street=阎巷49201号}, {id=286-94-4589, realName=张智渊, cellPhone=17184101643, universityName=中国农业大学, city=本溪, street=吴桥65589号}, {id=267-54-1474, realName=吴文轩, cellPhone=13026350677, universityName=东北体育大学, city=大连, street=蒋旁6号}, {id=728-22-5917, realName=张健柏, cellPhone=15806349158, universityName=东北艺术大学, city=张家界, street=彭栋63号}, {id=370-35-2072, realName=薛智渊, cellPhone=15728469717, universityName=西科技大学, city=阳泉, street=谢巷4号}, {id=318-44-1215, realName=于子轩, cellPhone=15335304204, universityName=北理工大学, city=大庆, street=潘栋248号}, {id=411-50-5126, realName=邓绍齐, cellPhone=17192233408, universityName=东北体育大学, city=昆山, street=阎街0502号}, {id=043-84-7711, realName=袁熠彤, cellPhone=15193803407, universityName=西南大学, city=安阳, street=孔桥817号}, {id=684-28-4959, realName=魏子涵, cellPhone=17562408960, universityName=西经贸大学, city=芜湖, street=孔路791号}, {id=153-43-1918, realName=白昊焱, cellPhone=17158657861, universityName=南体育大学, city=岳阳, street=谢街0号}, {id=650-23-4822, realName=石弘文, cellPhone=17728325193, universityName=西南艺术大学, city=曲靖, street=雷旁6957号}, {id=785-23-0911, realName=洪雪松, cellPhone=15012225810, universityName=东北大学, city=曲靖, street=戴桥7号}, {id=674-94-4905, realName=田潇然, cellPhone=17580294958, universityName=北农业大学, city=盐城, street=刘侬18706号}, {id=864-30-6912, realName=韩楷瑞, cellPhone=15673737519, universityName=南经贸大学, city=芜湖, street=高栋791号}, {id=247-71-7206, realName=王明, cellPhone=17165474302, universityName=北农业大学, city=吉林, street=周巷21号}, {id=693-16-3342, realName=薛立辉, cellPhone=18279159343, universityName=北大学, city=太原, street=杜中心087号}, {id=222-47-4051, realName=石智渊, cellPhone=17313032523, universityName=东南科技大学, city=绵阳, street=阎桥66787号}, {id=430-14-8252, realName=阎哲瀚, cellPhone=17070126553, universityName=中国科技大学, city=舟山, street=孟栋88号}]
2022-05-12 13:03:54.720  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : doGet - http://localhost:8080/userInfo?id=432-87-8071
2022-05-12 13:03:54.723  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : recv - 
2022-05-12 13:03:54.724 ERROR 17400 --- [           main] c.c.t.h.HttpFaceDemoApplicationTests     : Id信息不存在
2022-05-12 13:03:54.724  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : doGet - http://localhost:8080/userInfo/432-87-8071
2022-05-12 13:03:54.730  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : recv - 
2022-05-12 13:03:54.730 ERROR 17400 --- [           main] c.c.t.h.HttpFaceDemoApplicationTests     : Id信息不存在
2022-05-12 13:03:54.730  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : doGet - http://localhost:8080/auth_current
2022-05-12 13:03:54.733  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : recv - {"id":"138-32-6092","realName":"洪昊焱","cellPhone":"14513625571","universityName":"北艺术大学","city":"滨州","street":"邵侬0号"}
2022-05-12 13:03:54.794  INFO 17400 --- [           main] c.c.t.h.HttpFaceDemoApplicationTests     : 传递header path查询用户信息 => UserInfo(id=138-32-6092, realName=洪昊焱, cellPhone=14513625571, universityName=北艺术大学, city=滨州, street=邵侬0号)
2022-05-12 13:03:54.795  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : doGet - http://localhost:8080/auth_current
2022-05-12 13:03:54.797  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : recv - {"id":null,"realName":null,"cellPhone":null,"universityName":null,"city":null,"street":null}
2022-05-12 13:03:54.798  INFO 17400 --- [           main] c.c.t.h.HttpFaceDemoApplicationTests     : 传递header path查询用户信息 => UserInfo(id=null, realName=null, cellPhone=null, universityName=null, city=null, street=null)
2022-05-12 13:03:54.800  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : doGet - http://localhost:8080/xml
2022-05-12 13:03:54.817  INFO 17400 --- [           main] c.c.twoke.http.module.SimpleHttpSender   : recv - <Email><From>黎鸿煊</From><To>汪煜祺</To><Content>The Needle's Eye</Content></Email>
2022-05-12 13:03:54.879  INFO 17400 --- [           main] c.c.t.h.HttpFaceDemoApplicationTests     : xml查询email信息 => HttpFaceDemoApplicationTests.Email(from=黎鸿煊, to=汪煜祺, content=The Needle's Eye)
```

