package cn.com.twoke.http.parser;
import cn.com.twoke.http.config.RequestContext;
import cn.com.twoke.http.creator.ReturnCreator;
import cn.com.twoke.http.type.ContentType;
import cn.com.twoke.http.type.MethodType;
import cn.com.twoke.http.utils.ObjectMapperUtil;
import cn.com.twoke.http.utils.OkHttpUtil;
import cn.com.twoke.http.utils.TikaUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;


/**
 * Post 请求解析
 *
 * @author TwoKe
 * @version $Id: $Id
 */
public class PostParser implements ApiParser {

    /** {@inheritDoc} */
    @Override
    public Object parse(RequestContext requestContext) {

        Request.Builder requestBuilder = OkHttpUtil.createRequestBuilder(requestContext);
        ContentType contentType = requestContext.getContentType();

        RequestBody body = createBody(contentType, requestContext);

        Request request = requestBuilder.post(body).build();
        try {
            Response response = OkHttpUtil.call(request).execute();
            return ReturnCreator.build()
                    .create(response.body().string(), requestContext.getFunction().getReturnType(),requestContext.getReturnType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RequestBody createBody(ContentType contentType, RequestContext requestContext) {
        if (ContentType.JSON.equals(requestContext.getContentType())) {
            try {
                String data = ObjectMapperUtil.getInstance().writeValueAsString(requestContext.getBody());
                return RequestBody.create(MediaType.parse(contentType.contentType()), data);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if (ContentType.FORM.equals(requestContext.getContentType())) {
            Object body = requestContext.getBody();

            Class<?> clazz = body.getClass();

            Field[] fields = clazz.getDeclaredFields();
            FormBody.Builder requestBuilder = new FormBody.Builder();

            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(body);
                    requestBuilder.add(field.getName(), value.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }finally {
                    field.setAccessible(false);
                }
            }
            return requestBuilder.build();
        } else if (ContentType.MULTIPART.equals(requestContext.getContentType())) {
            Object body = requestContext.getBody();

            Class<?> clazz = body.getClass();

            Field[] fields = clazz.getDeclaredFields();
            MultipartBody.Builder requestBuilder = new MultipartBody.Builder();

            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(body);
                    if (value instanceof  File) {
                        File file = (File) value;
                        String detect = TikaUtil.getInstance().detect(file);
                        RequestBody fileBody = RequestBody.create(MediaType.parse(detect), file);
                        requestBuilder.addFormDataPart(
                                field.getName(),
                                file.getName(), fileBody);
                    } else {
                        requestBuilder.addFormDataPart(
                                field.getName(),
                                value.toString());
                    }
                } catch (IllegalAccessException | IOException e) {
                    e.printStackTrace();
                }finally {
                    field.setAccessible(false);
                }
            }
            return requestBuilder.build();
        }
        return null;
    }


    /** {@inheritDoc} */
    @Override
    public MethodType getMethodType() {
        return MethodType.POST;
    }

}
