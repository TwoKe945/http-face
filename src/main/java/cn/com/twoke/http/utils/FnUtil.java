package cn.com.twoke.http.utils;

import java.util.function.Predicate;

/**
 * <p>函数工具</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 0:59
 */
public final class FnUtil {

    /**
     * <p>judge.</p>
     *
     * @param text a T object.
     * @param predicate a {@link java.util.function.Predicate} object.
     * @param <T> a T object.
     * @return a boolean.
     */
    public static  <T> boolean judge(T text, Predicate<T> predicate) {
        return predicate.test(text);
    }


}
