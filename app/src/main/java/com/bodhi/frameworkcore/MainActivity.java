package com.bodhi.frameworkcore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bodhi.framecore.FrameCore;
import com.bodhi.framecore.activity.BaseBarActivity;
import com.bodhi.framecore.activity.BaseTitleBar;
import com.bodhi.frameworkcore.activity.TestActivity;

import butterknife.OnClick;

public class MainActivity extends BaseBarActivity {

    @OnClick(R.id.mainTv)
    void toTestActivity() {
        Toast.makeText(this,"aa",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        findViewById(R.id.mainTv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"BB",Toast.LENGTH_SHORT).show();
//
//                startActivity(new Intent(MainActivity.this, TestActivity.class));
//            }
//        });
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
