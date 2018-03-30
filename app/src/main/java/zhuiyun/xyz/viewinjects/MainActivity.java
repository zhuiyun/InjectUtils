package zhuiyun.xyz.viewinjects;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import zhuiyun.xyz.injectutils.AccessPermission;
import zhuiyun.xyz.injectutils.BindView;
import zhuiyun.xyz.injectutils.InjectUtils;
import zhuiyun.xyz.injectutils.PermissionHelper;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.inject(this);
        tv.setText("bindView");

    }


    public void play(View view) {
        tv.setText("bindClick");
        Log.e("gao", "play: ");
        PermissionHelper.with(this).requestCode(0).requestPersion(new String[]{Manifest.permission.CALL_PHONE}).permissionRequest();
//        callPhone();

    }

    @AccessPermission(requestCode = 0)
    private void callPhone() {
        Log.e("gao", "callPhone: ");
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:110"));
        Log.e("gao", "callPhone: 1");
        startActivity(intent);
    }
}
