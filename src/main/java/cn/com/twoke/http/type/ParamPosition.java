package cn.com.twoke.http.type;

/**
 * <p>ParamPosition class.</p>
 *
 * @author TwoKe
 * @title: PosType
 * @projectName http-face
 * @description: 参数位置
 * @date 2022/5/1115:26
 * @version $Id: $Id
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
     * body
     */
    BODY,
    /**
     * request header
     */
    HEADER,
    /**
     * 默认类型
     */
    DEFAULT
    ;
}
