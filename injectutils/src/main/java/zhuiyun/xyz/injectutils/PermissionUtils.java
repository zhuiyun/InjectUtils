package zhuiyun.xyz.injectutils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gwy on 2018/3/30.
 *
 * @author:zhuiyun
 */

public class PermissionUtils {
    private PermissionUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    //判断android版本是否大于6.0
    public static boolean isOverMarshmallow() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            return true;
        } else {
            return false;
        }
    }

    public static void excuteSuccess(Object object, int requestCode) {
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            AccessPermission annotation = method.getAnnotation(AccessPermission.class);
            if (annotation != null) {
                method.setAccessible(true);
                if (annotation.requestCode() == requestCode) {
                    try {
                        Log.d("gao", "excuteSuccess: 4"+method.getName());
                        method.invoke(object);
                    } catch (IllegalAccessException e) {
                        Log.d("gao", "excuteSuccess: "+e.toString());

                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        Log.d("gao", "excuteSuccess: "+e.toString());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static List<String> isAllowPermission(Object object, String[] mRequestPermission) {
        List<String> deniedPermission=new ArrayList<>();
        for (int i = 0; i < mRequestPermission.length; i++) {
            int m = ContextCompat.checkSelfPermission(getActivity(object), mRequestPermission[i]);
            if(m!= PackageManager.PERMISSION_GRANTED){
                deniedPermission.add(mRequestPermission[i]);
            }
        }
//        for(String requestPermission:mRequestPermission){
//
//            Log.e("gao", "isAllowPermission: "+deniedPermission.size());
//        }
        return deniedPermission;
    }

    public static Activity getActivity(Object object) {
        if(object instanceof Activity){
            return (Activity) object;
        }else{
            return ((Fragment) object).getActivity();
        }
    }
}
