package zhuiyun.xyz.viewinjects.net;

import android.util.Log;

import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gwy on 2018/4/4.
 *
 * @author:zhuiyun
 */

public class RetrofitUtils<T> {

    static Retrofit retrofit;
    static {
        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor()).build();
        retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(okHttpClient).baseUrl("http://www.wanandroid.com/").build();
    }
    public static void getMsg(final RequestInterface requestInterface){
        NetInterface netInterface = retrofit.create(NetInterface.class);
        netInterface.getMsg().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<JsonObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JsonObject jsonObject) {
                requestInterface.onSuccess(jsonObject);
                Log.e("gao", "onNext: "+jsonObject.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("gao", "onError: "+e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("gao", "onComplete: ");
            }
        });
    }

    public static void GsonConvert(String js){

    }
}
