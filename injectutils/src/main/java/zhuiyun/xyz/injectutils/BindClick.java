package zhuiyun.xyz.injectutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绑定点击事件
 * Created by gwy on 2018/3/26.
 *
 * @author:zhuiyun
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindClick {
    int[] value();
}
