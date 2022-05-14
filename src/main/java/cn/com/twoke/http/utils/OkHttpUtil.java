package cn.com.twoke.http.utils;

import cn.com.twoke.http.config.ParamData;
import cn.com.twoke.http.config.RequestContext;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;

/**
 * <p>Ok Http工具</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 2:44
 */
public final class OkHttpUtil {

    private static OkHttpClient instance;

    /**
     * <p>Getter for the field <code>instance</code>.</p>
     *
     * @return a {@link okhttp3.OkHttpClient} object.
     */
    public static synchronized OkHttpClient getInstance() {
        if (instance == null) {
            instance = new OkHttpClient();
        }
        return instance;
    }

    /**
     * <p>call.</p>
     *
     * @param request a {@link okhttp3.Request} object.
     * @return a {@link okhttp3.Call} object.
     */
    public static Call call(Request request) {
        OkHttpClient instance = getInstance();
        return instance.newCall(request);
    }


    /**
     * <p>createRequest.</p>
     *
     * @param requestContext a {@link cn.com.twoke.http.config.RequestContext} object.
     * @param body a {@link okhttp3.RequestBody} object.
     * @return a {@link okhttp3.Request} object.
     */
    public static Request createRequest(RequestContext requestContext, RequestBody body) {
        return createRequestBuilder(requestContext)
                .method(requestContext.getMethod().toString(), body)
                .build();
    }

    /**
     * <p>createRequestBuilder.</p>
     *
     * @param requestContext a {@link cn.com.twoke.http.config.RequestContext} object.
     * @return a {@link okhttp3.Request.Builder} object.
     */
    public static Request.Builder createRequestBuilder(RequestContext requestContext) {
        Request.Builder builder = new Request
                .Builder()
                .url(requestContext.getUrl());

        ParamData headers = requestContext.getHeaders();
        for (Object obj : headers.entrySet()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>)obj;
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        return builder;
    }

}
