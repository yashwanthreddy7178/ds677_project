package com.dream.application.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dream.application.R;
import com.user.fun.library.util.StatusBarUtils;

/**
 * Created on 2017/6/28.
 * author chen_ping
 * Email yy_cping@163.com
 * Edit androidStudio
 */

public class OriginalActivity extends AppCompatActivity {
    protected int mStatusBarColor = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 无标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setWindowAttrs();


    }

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            if (mStatusBarColor == 0)
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R
                        .color.colorPrimary));

            else
                getWindow().setStatusBarColor(ContextCompat.getColor
                        (this, mStatusBarColor));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT <
                Build.VERSION_CODES.LOLLIPOP) {//4.4到5.0
            View view = new View(this);
            if (mStatusBarColor == 0)
                view.setBackgroundColor(ContextCompat.getColor(this, R
                        .color.colorPrimary));
            else
                view.setBackgroundColor(ContextCompat.getColor
                        (this, mStatusBarColor));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT,
                    StatusBarUtils.getStatusBarHeight(this));
            ViewGroup decorView = (ViewGroup) findViewById(android.R.id.content);
            decorView.addView(view, params);
        }

    }


    protected void setWindowAttrs() {
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
//                .LayoutParams.FLAG_FULLSCREEN);
    }


}
