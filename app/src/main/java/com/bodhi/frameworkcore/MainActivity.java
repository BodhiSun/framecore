package com.bodhi.frameworkcore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bodhi.framecore.FrameCore;
import com.bodhi.framecore.activity.BaseBarActivity;
import com.bodhi.framecore.activity.BaseTitleBar;
import com.bodhi.frameworkcore.activity.TestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseBarActivity {

    @OnClick(R.id.mainTv)
    void toTestActivity() {
        startActivity(new Intent(this, TestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int contentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseTitleBar titleBar() {
        return null;
    }

    @Override
    protected boolean darkStatusBar() {
        return false;
    }
}
