package zhuiyun.xyz.viewinjects;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by gwy on 2018/4/2.
 *
 * @author:zhuiyun
 */

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        invalidate();
    }


}
