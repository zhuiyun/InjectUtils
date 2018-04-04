package zhuiyun.xyz.viewinjects;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by gwy on 2018/4/2.
 *
 * @author:zhuiyun
 */

public class MyRelativelayout extends RelativeLayout {
    public MyRelativelayout(Context context) {
        this(context, null);
    }

    public MyRelativelayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRelativelayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("gao", "onMeasure: ");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("gao", "onLayout: ");

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("gao", "onSizeChanged: ");

    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
        Log.e("gao", "measureChild: ");
    }
}
