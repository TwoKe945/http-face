package cn.com.twoke.http.exp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>HttpSenderException class.</p>
 *
 * @author TwoKe
 * @title: HttpSenderExecption
 * @projectName http-face
 * @description: HttpSenderException
 * @date 2022/5/1113:23
 * @version $Id: $Id
 */
@Data
@Accessors(chain = true)
public class HttpFaceException extends RuntimeException {

    /**
     * 结果
     */
    private String result;

    /**
     * <p>Constructor for HttpSenderException.</p>
     */
    public HttpFaceException() {
    }

    /**
     * <p>Constructor for HttpSenderException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public HttpFaceException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for HttpSenderException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Throwable} object.
     */
    public HttpFaceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Constructor for HttpSenderException.</p>
     *
     * @param cause a {@link java.lang.Throwable} object.
     */
    public HttpFaceException(Throwable cause) {
        super(cause);
    }

    /**
     * <p>Constructor for HttpSenderException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Throwable} object.
     * @param enableSuppression a boolean.
     * @param writableStackTrace a boolean.
     */
    public HttpFaceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
