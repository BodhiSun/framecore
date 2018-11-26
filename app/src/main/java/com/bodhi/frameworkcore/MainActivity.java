package com.bodhi.frameworkcore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bodhi.framecore.FrameCore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameCore.getInstance().init(this);
    }
}
