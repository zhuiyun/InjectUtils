package zhuiyun.xyz.viewinjects.net;

/**
 * Created by gwy on 2018/4/4.
 *
 * @author:zhuiyun
 */

public interface RequestInterface<T>{
    void onSuccess(T type);
    void onFail(String msg);
}
