package zhuiyun.xyz.viewinjects.net;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by gwy on 2018/4/4.
 *
 * @author:zhuiyun
 */

public interface NetInterface<T> {
    @GET("banner/json")
    Observable<T> getMsg();
}
