package zhuiyun.xyz.injectutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绑定控件
 * Created by gwy on 2018/3/26.
 *
 * @author:zhuiyun
 */

/**
 * 属性注解和运行时注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    int value();
}
