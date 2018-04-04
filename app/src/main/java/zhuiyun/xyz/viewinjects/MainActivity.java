package zhuiyun.xyz.viewinjects;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import zhuiyun.xyz.injectutils.AccessPermission;
import zhuiyun.xyz.injectutils.BindClick;
import zhuiyun.xyz.injectutils.BindView;
import zhuiyun.xyz.injectutils.InjectUtils;
import zhuiyun.xyz.injectutils.PermissionHelper;
import zhuiyun.xyz.viewinjects.net.RetrofitUtils;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.inject(this);
        RetrofitUtils.getMsg();

    }

    @BindClick(R.id.btn)
    public void changeText() {
        tv.setText("text changed");
    }

    public void play(View view) {

        tv.setText("bindClick");
//        PermissionHelper.with(this).requestCode(0).requestPersion(new String[]{Manifest.permission.CALL_PHONE}).permissionRequest();
//        callPhone();

    }

    @AccessPermission(requestCode = 0)
    private void callPhone() {
        Log.d("gao", "callPhone: ");
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:17620436060"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.resultPermissions(this, requestCode, permissions);
    }
}
