package cn.com.twoke.http.config;

/**
 * @author TwoKe
 * @title: RequestConfig
 * @projectName http-face
 * @description: 请求配置
 * @date 2022/5/1114:38
 */
public class RequestConfig {

    public static final ParamData<String> doGetConfig = ParamData.<String>newParam()
            .putData("accept", "*/*")
            .putData("connection", "Keep-Alive")
            .putData("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

    public static final ParamData<String> doPostConfig = ParamData.<String>newParam()
            .putData("accept", "*/*")
            .putData("connection", "Keep-Alive")
            .putData("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
            .putData("Accept-Charset", "utf-8")
            .putData("contentType", "utf-8");

    public static final ParamData<String> doPostByteConfig = ParamData.<String>newParam()
            .putData("accept", "*/*")
            .putData("connection", "Keep-Alive")
            .putData("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
            .putData("Accept-Charset", "utf-8")
            .putData("contentType", "application/octet-stream");

    public static final ParamData<String> doPostSSLConfig = ParamData.<String>newParam()
            .putData("accept", "*/*")
            .putData("connection", "Keep-Alive")
            .putData("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
            .putData("Accept-Charset", "utf-8")
            .putData("contentType", "utf-8");
}
