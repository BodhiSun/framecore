package com.bodhi.frameworkcore.bar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bodhi.framecore.activity.BaseTitleBar;
import com.bodhi.frameworkcore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/26 17:10
 * desc :
 */
public class TitleBar extends BaseTitleBar {

    @BindView(R.id.ll_title_bar)
    RelativeLayout titleBarLL;

    @BindView(R.id.ll_btn_left)
    LinearLayout leftLL;

    @BindView(R.id.tv_btn_left)
    TextView leftTV;

    @BindView(R.id.ll_btn_right)
    LinearLayout rightLL;

    @BindView(R.id.tv_btn_right)
    TextView rightTV;

    @BindView(R.id.tv_title)
    TextView titleTV;

    @BindView(R.id.v_space)
    View spaceV;

    @OnClick(R.id.tv_btn_left)
    void clickLeftTV() {
        if (leftAction != null) {
            leftAction.run();
        }
    }

    @OnClick(R.id.tv_btn_right)
    void clickRightTV() {
        if (rightAction != null) {
            rightAction.run();
        }
    }

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onInit() {
        ButterKnife.bind(this);
        leftAction = new Runnable() {
            @Override
            public void run() {
                finishActivity();
            }
        };
    }

    @Override
    protected int layoutId() {
        return R.layout.title_bar;
    }

    public void title(String title) {
        titleTV.setText(title);
    }

    public void titleColor(int color) {
        titleTV.setTextColor(color);
    }

    public void setLeftText(String leftText) {
        leftTV.setText(leftText);
        leftTV.setVisibility(VISIBLE);
    }

    public void setLeftTextColor(int color) {
        leftTV.setTextColor(color);
    }

    public void setLeftDrawable(int drawable){
        Drawable drawable1Left=getResources().getDrawable(drawable);
        leftTV.setCompoundDrawablesWithIntrinsicBounds(drawable1Left,null,null,null);
//        leftTV.setCompoundDrawablePadding(4);
    }

    public void setRightText(String rightText) {
        rightTV.setText(rightText);
        rightTV.setVisibility(VISIBLE);
    }

    public void setRightTextColor(int color) {
        rightTV.setTextColor(color);
    }

    public void setTitleBarBg(int color) {
        titleBarLL.setBackgroundColor(color);
    }

    public void setLeftAction(Runnable la) {
        this.leftAction = la;
    }

    public void setRightAction(Runnable ra) {
        this.rightAction = ra;
    }


}
