package zhuiyun.xyz.injectutils;

import android.app.Activity;
import android.view.View;

/**
 * Created by gwy on 2018/3/26.
 *
 * @author:zhuiyun
 */

public class InjectAdapter {
    private View view;
    private Activity activity;

    public InjectAdapter(Activity activity) {
        this.activity = activity;
    }

    public InjectAdapter(View view) {
        this.view = view;
    }

    public View findViewById(int viewId){
        View tempView=activity!=null?activity.findViewById(viewId):view.findViewById(viewId);
        return tempView;
    }
}
