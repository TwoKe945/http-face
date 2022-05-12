package cn.com.twoke.http;

import java.util.Map;

/**
 * <p>IHttpSender interface.</p>
 *
 * @author TwoKe
 * @title: IHttpSender
 * @projectName http-face
 * @description: 请求发送者
 * @date 2022/5/1113:06
 * @version $Id: $Id
 */
public interface IHttpSender {

    /**
     * Post请求
     *
     * @param url 请求地址
     * @param data 数据 key1=value1&key2=value2
     * @return 所代表远程资源的响应结果
     * @param param a {@link java.lang.String} object.
     * @param config a {@link java.util.Map} object.
     */
    String doPost(String url, String param, String data, Map<String, String> config);

    /**
     * Post 请求
     *
     * @param url 请求地址
     * @param data 数据
     * @return 所代表远程资源的响应结果
     * @param params a {@link java.util.Map} object.
     * @param config a {@link java.util.Map} object.
     */
    String doPost(String url,  Map<String, Object> params, Map<String, Object> data, Map<String, String> config);

    /**
     * Get 请求
     *
     * @param url 请求地址
     * @param data 数据
     * @return 所代表远程资源的响应结果
     * @param config a {@link java.util.Map} object.
     */
    String doGet(String url, String data, Map<String, String> config);

    /**
     * Get 请求
     *
     * @param url 请求地址
     * @param data 数据
     * @return 所代表远程资源的响应结果
     * @param config a {@link java.util.Map} object.
     */
    String doGet(String url, Map<String, Object> data, Map<String, String> config);

    /**
     * 以二进制流的形式，向指定 URL 发送POST方法的请求 作为 http 的 body
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 待传输文件的字节数组
     * @return 所代表远程资源的响应结果
     * @param config a {@link java.util.Map} object.
     */
    String doPostByByte(String url, byte[] param, Map<String, String> config);

    /**
     * SSL 安全检测
     *
     * @param url 发送请求的 URL
     * @param param a {@link java.lang.String} object.
     * @param config a {@link java.util.Map} object.
     * @return a {@link java.lang.String} object.
     */
    String doPostSSL(String url, String param, Map<String, String> config);
}
