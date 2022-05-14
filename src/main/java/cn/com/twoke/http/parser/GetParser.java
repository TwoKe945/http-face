package cn.com.twoke.http.parser;
import cn.com.twoke.http.config.RequestContext;
import cn.com.twoke.http.creator.ReturnCreator;
import cn.com.twoke.http.type.MethodType;
import cn.com.twoke.http.utils.OkHttpUtil;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


/**
 * Get 请求解析
 *
 * @author TwoKe
 * @version $Id: $Id
 */
public class GetParser implements ApiParser {

    /** {@inheritDoc} */
    @Override
    public Object parse(RequestContext requestContext) {

        Request.Builder requestBuilder = OkHttpUtil.createRequestBuilder(requestContext);
        Request request = requestBuilder.get().build();
        try {
            Response response = OkHttpUtil.call(request).execute();
            return ReturnCreator.build()
                    .create(response.body().string(), requestContext.getFunction().getReturnType(),requestContext.getReturnType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public MethodType getMethodType() {
        return MethodType.GET;
    }

}
