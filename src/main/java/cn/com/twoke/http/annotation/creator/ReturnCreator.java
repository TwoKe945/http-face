package cn.com.twoke.http.annotation.creator;

/**
 * @author TwoKe
 * @title: ReturenCreator
 * @projectName http-face
 * @description: 结果创造器
 * @date 2022/5/1122:10
 */
public final class ReturnCreator {

    private ReturnCreator() {}

    public static ReturnCreator build() { return new ReturnCreator(); }
}
