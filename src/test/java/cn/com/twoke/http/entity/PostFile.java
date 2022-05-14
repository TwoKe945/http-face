package cn.com.twoke.http.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

/**
 * <p>TODO</p>
 *
 * @author TwoKe
 * @version 1.0.0
 * @since 2022/5/14 10:12
 */
@Data
@AllArgsConstructor
public class PostFile {

    private File file;

    private String name;

    private String url;

}
