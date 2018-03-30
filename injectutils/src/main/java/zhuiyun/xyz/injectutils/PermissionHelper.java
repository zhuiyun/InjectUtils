package zhuiyun.xyz.injectutils;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gwy on 2018/3/30.
 *
 * @author:zhuiyun
 */

public class PermissionHelper {
    private Object object;
    private  int requestCode;
    private  List<String> permissions=new ArrayList<>();

    public  PermissionHelper(Object object) {
        this.object = object;
    }
    public static PermissionHelper with(Activity mActivity) {
        return  new PermissionHelper(mActivity);
    }
    public static PermissionHelper with(Fragment fragment) {
        return  new PermissionHelper(fragment);
    }
    public  PermissionHelper requestCode(int code) {
        this.requestCode=code;
        return  new PermissionHelper(this);
    }

    /**
     * 添加需要请求的权限
     * @param s
     * @return
     */
    public PermissionHelper requestPersion(String... s) {
        for (String str: s) {
            permissions.add(str);
        }
        return  new PermissionHelper(this);
    }




    //请求权限
    public void permissionRequest() {

        //大于6.0
        if(PermissionUtils.isOverMarshmallow()){
            List<Integer> list=new ArrayList<>();

            List<String> disAllowPermission = PermissionUtils.isAllowPermission(object, permissions);
            if(disAllowPermission.size()>0){
                Log.e("gao", "request: 4");
                ActivityCompat.requestPermissions(PermissionUtils.getActivity(object),disAllowPermission.toArray(new String[disAllowPermission.size()]),requestCode);
            }else{
                Log.e("gao", "request: 5");
                PermissionUtils.excuteSuccess(object, requestCode);
            }
        }else{
            Log.e("gao", "request: 3");
            //注解+反射
            PermissionUtils.excuteSuccess(object, requestCode);
        }
    }
}
