package cn.com.twoke.http.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>TODO</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 9:14
 */
public final class ObjectMapperUtil {

    private static ObjectMapper instance;

    /**
     * <p>Getter for the field <code>instance</code>.</p>
     *
     * @return a {@link com.fasterxml.jackson.databind.ObjectMapper} object.
     */
    public static synchronized ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapper();
        }
        return instance;
    }

}
