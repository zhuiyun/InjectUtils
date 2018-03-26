package zhuiyun.xyz.injectutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绑定点击事件间隔,防重复点击
 * Created by gwy on 2018/3/26.
 *
 * @author:zhuiyun
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickInterval {
    long value();
}
