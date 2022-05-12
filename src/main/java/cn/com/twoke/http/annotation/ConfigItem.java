package cn.com.twoke.http.annotation;

/**
 * <p>ConfigItem class.</p>
 *
 * @author TwoKe
 * @title: Config
 * @projectName http-face
 * @description: 配置
 * @date 2022/5/1118:05
 * @version $Id: $Id
 */
public @interface ConfigItem {

    /**
     * 配置名称
     * @return
     */
    String name();

    /**
     * 配置值
     * @return
     */
    String value();

}
