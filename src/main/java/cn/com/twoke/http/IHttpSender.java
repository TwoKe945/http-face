package cn.com.twoke.http;

import java.util.Map;

/**
 * @author TwoKe
 * @title: IHttpSender
 * @projectName http-face
 * @description: 请求发送者
 * @date 2022/5/1113:06
 */
public interface IHttpSender {

    /**
     * Post请求
     * @param url 请求地址
     * @param data 数据 key1=value1&key2=value2
     * @return 所代表远程资源的响应结果
     */
    String doPost(String url, String param, String data, Map<String, String> config);

    /**
     * Post 请求
     * @param url 请求地址
     * @param data 数据
     * @return 所代表远程资源的响应结果
     */
    String doPost(String url,  Map<String, Object> params, Map<String, Object> data, Map<String, String> config);

    /**
     * Get 请求
     * @param url 请求地址
     * @param data 数据
     * @return 所代表远程资源的响应结果
     */
    String doGet(String url, String data, Map<String, String> config);

    /**
     * Get 请求
     * @param url 请求地址
     * @param data 数据
     * @return 所代表远程资源的响应结果
     */
    String doGet(String url, Map<String, Object> data, Map<String, String> config);

    /**
     * 以二进制流的形式，向指定 URL 发送POST方法的请求 作为 http 的 body
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 待传输文件的字节数组
     * @return 所代表远程资源的响应结果
     */
    String doPostByByte(String url, byte[] param, Map<String, String> config);

    /**
     * SSL 安全检测
     * @param url 发送请求的 URL
     * @param param
     * @return
     */
    String doPostSSL(String url, String param, Map<String, String> config);
}
