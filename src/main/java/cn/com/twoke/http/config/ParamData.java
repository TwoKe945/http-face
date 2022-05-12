package cn.com.twoke.http.config;

import java.util.LinkedHashMap;

/**
 * <p>ParamData class.</p>
 *
 * @author TwoKe
 * @param <T>
 * @version $Id: $Id
 */
public class ParamData<T> extends LinkedHashMap<String, T> {

    private ParamData() {}

    /**
     * <p>newParam.</p>
     *
     * @param <T> a T object.
     * @return a {@link cn.com.twoke.http.config.ParamData} object.
     */
    public static <T> ParamData<T> newParam() {
        return new ParamData();
    }

    /**
     * <p>putData.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param value a T object.
     * @return a {@link cn.com.twoke.http.config.ParamData} object.
     */
    public ParamData<T> putData(String key, T value) {
        super.put(key, value);
        return this;
    }
}
