package cn.com.twoke.http.exp;

/**
 * @author TwoKe
 * @title: HttpSenderExecption
 * @projectName http-face
 * @description: HttpSenderException
 * @date 2022/5/1113:23
 */
public class HttpSenderException extends RuntimeException {

    public HttpSenderException() {
    }

    public HttpSenderException(String message) {
        super(message);
    }

    public HttpSenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpSenderException(Throwable cause) {
        super(cause);
    }

    public HttpSenderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
