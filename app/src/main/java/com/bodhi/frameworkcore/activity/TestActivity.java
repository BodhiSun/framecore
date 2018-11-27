package com.bodhi.frameworkcore.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.bodhi.framecore.activity.BaseBarActivity;
import com.bodhi.framecore.activity.BaseTitleBar;
import com.bodhi.frameworkcore.R;
import com.bodhi.frameworkcore.bar.TitleBar;

import butterknife.OnClick;

public class TestActivity extends BaseBarActivity {

    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int contentLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected BaseTitleBar titleBar() {
        if (titleBar == null) {
            titleBar = new TitleBar(this);
            titleBar.title("测试页面");
            titleBar.titleColor(Color.WHITE);
            titleBar.setLeftText("返回");
            titleBar.setLeftTextColor(android.R.color.white);
            titleBar.setLeftDrawable(R.drawable.back_icon2);
            titleBar.bindActivity(this);
        }

        return titleBar;
    }

    @Override
    protected boolean darkStatusBar() {
        return false;
    }
}
