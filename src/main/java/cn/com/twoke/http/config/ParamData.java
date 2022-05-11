package cn.com.twoke.http.config;

import java.util.LinkedHashMap;

/**
 *
 * @author TwoKe
 * @param <T>
 */
public class ParamData<T> extends LinkedHashMap<String, T> {

    private ParamData() {}

    public static <T> ParamData<T> newParam() {
        return new ParamData();
    }

    public ParamData<T> putData(String key, T value) {
        super.put(key, value);
        return this;
    }
}
