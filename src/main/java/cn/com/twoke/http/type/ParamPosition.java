package cn.com.twoke.http.type;

/**
 * @author TwoKe
 * @title: PosType
 * @projectName http-face
 * @description: 参数位置
 * @date 2022/5/1115:26
 */
public enum ParamPosition {
    /**
     * restful
     */
    PATH,
    /**
     * ?key1=value1&key2=value1
     */
    URL,
    /**
     * body里面
     */
    BODY,
    /**
     * 请求头里面
     */
    HEADER,
    /**
     * 默认类型 比如 get 请求默认是 URL, post 请求默认是 body
     */
    DEFAULT
    ;
}
