package cn.com.twoke.http.annotation;

/**
 * @author TwoKe
 * @title: Config
 * @projectName http-face
 * @description: 配置
 * @date 2022/5/1118:05
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
