package com.bodhi.framecore.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bodhi.framecore.FrameCore;
import com.bodhi.framecore.R;
import com.bodhi.framecore.permission.PermissionRequestListener;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/26 14:05
 * desc :
 */
public abstract class BaseBarActivity<T extends BaseTitleBar> extends AppCompatActivity {

    private LinearLayout baseView;

    private LinearLayout titleLL;

    private FrameLayout contentFL;

    private PermissionRequestListener permissionRequestListener;

    private int statusBarColor = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_bar);
        baseView = findViewById(R.id.ll_base_bar_view);
        titleLL = findViewById(R.id.ll_base_bar_title);
        contentFL = findViewById(R.id.ll_base_bar_content);

        //设置状态栏背景色
        statusBarColor = FrameCore.getInstance().getDefaultStatusBarColor();
        StatusBarUtil.setColor(this, statusBarColor, 0);
        titleLL.setBackgroundColor(statusBarColor);

        //设置状态栏字体颜色黑或白
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (darkStatusBar()) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        init();

        ButterKnife.bind(this);
    }

    private void init() {
        if (titleBar() != null) {
            titleLL.addView(titleBar());
        } else {
            titleLL.setVisibility(View.GONE);
        }

        contentFL.addView(LayoutInflater.from(this).inflate(contentLayout(), null));
    }

    protected abstract int contentLayout();

    protected abstract T titleBar();

    protected abstract boolean darkStatusBar();

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void checkPermissions(PermissionRequestListener listener, String... list) {
        permissionRequestListener = listener;

        List<String> lackedPermission = new ArrayList<>();

        for (String permission : list) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                lackedPermission.add(permission);
            }
        }

        if (lackedPermission.isEmpty()) {
            if (permissionRequestListener != null) {
                permissionRequestListener.onGrantSuccess();
            }
        } else {
            String[] requesPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requesPermissions);
            requestPermissions(requesPermissions, 101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101 && hasAllPermissionsGranted(grantResults)) {
            if (permissionRequestListener != null) {
                permissionRequestListener.onGrantSuccess();
            }
        } else {
            if (permissionRequestListener != null) {
                permissionRequestListener.onGrantFail();
            }
        }
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    public void post(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }

    public void postDelayed(Runnable runnable, long delay) {
        if (runnable == null) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable, delay);
    }

    public void toast(String content) {
        Toast.makeText(BaseBarActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    public void toastMainThread(final String content) {
        post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseBarActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void log(String tag, String msg) {
        FrameCore.getInstance().doLog(tag, msg);
    }
}
