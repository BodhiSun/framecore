package com.bodhi.framecore.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/26 12:05
 * desc :
 */
public abstract class BaseTitleBar extends RelativeLayout {

    private BaseBarActivity baseBarActivity;

    protected Runnable leftAction;
    protected Runnable rightAction;

    public BaseTitleBar(Context context) {
        super(context);
        init(context);
    }

    public BaseTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(layoutId(), this);
        onInit();
    }

    public void bindActivity(BaseBarActivity host) {
        baseBarActivity = host;
    }

    public void finishActivity() {
        if (baseBarActivity != null) {
            baseBarActivity.finish();
        }
    }

    protected abstract void onInit();

    protected abstract int layoutId();
}
