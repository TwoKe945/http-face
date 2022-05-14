package cn.com.twoke.http.utils;


import org.apache.tika.Tika;

/**
 * <p>TiKda工具</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 10:07
 */
public final class TikaUtil {

    private static Tika instance;

    /**
     * <p>Getter for the field <code>instance</code>.</p>
     *
     * @return a {@link org.apache.tika.Tika} object.
     */
    public static synchronized Tika getInstance() {
        if (instance == null) {
            instance = new Tika();
        }
        return instance;
    }
}
