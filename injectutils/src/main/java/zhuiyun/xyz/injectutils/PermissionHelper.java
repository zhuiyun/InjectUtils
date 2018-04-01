package zhuiyun.xyz.injectutils;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

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
    private  String[] permissions;

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
        return  this;
    }

    /**
     * 添加需要请求的权限
     * @param s
     * @return
     */
    public PermissionHelper requestPersion(String... s) {
        this.permissions=s;
        return  this;
    }




    //请求权限
    public void permissionRequest() {
        Log.d("gao", "permissionRequest: ");
        //大于6.0
        if(PermissionUtils.isOverMarshmallow()){

            List<String> disAllowPermission = PermissionUtils.isAllowPermission(object, permissions);
            if(disAllowPermission.size()>0){
                Log.d("gao", "permissionRequest: ");
                ActivityCompat.requestPermissions(PermissionUtils.getActivity(object),disAllowPermission.toArray(new String[disAllowPermission.size()]),requestCode);
            }else{
                Log.d("gao", "permissionRequest: 1");
                PermissionUtils.excuteSuccess(object, requestCode);
            }
        }else{
            //注解+反射
            PermissionUtils.excuteSuccess(object, requestCode);
        }
    }

    public void permissionResult() {

    }

    public static void resultPermissions(Activity mActivity, int requestCode, String[] permissions) {
        List<String> disAllowPermission = PermissionUtils.isAllowPermission(mActivity, permissions);
        if(disAllowPermission.size()>0){
            Log.d("gao", "permissionResult: ");
            Toast.makeText(PermissionUtils.getActivity(mActivity),"权限已拒绝",Toast.LENGTH_SHORT).show();
//            ActivityCompat.requestPermissions(PermissionUtils.getActivity(object),disAllowPermission.toArray(new String[disAllowPermission.size()]),requestCode);
        }else{
            Log.d("gao", "permissionResult: 1");
            PermissionUtils.excuteSuccess(mActivity, requestCode);
        }
    }
}
