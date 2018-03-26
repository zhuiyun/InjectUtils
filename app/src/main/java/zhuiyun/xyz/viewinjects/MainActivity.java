package zhuiyun.xyz.viewinjects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import zhuiyun.xyz.injectutils.BindClick;
import zhuiyun.xyz.injectutils.BindView;
import zhuiyun.xyz.injectutils.ClickInterval;
import zhuiyun.xyz.injectutils.InjectUtils;

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

    @BindClick(R.id.tv)
    @ClickInterval(5000)
    public void play(){
        tv.setText("bindClick");
        Log.e("gao", "play: ");
    }
}
