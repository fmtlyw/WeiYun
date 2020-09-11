package com.lyw.commonutils.utils;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuAn_Zhangsongzhou on 2017/8/23.
 */

public class StatusBarUtil {
    private static List<View> mStatusBarViewList;

    /**
     * before setContentView()
     *
     * @param activity
     */
    public static void setStatusBarTranslucent(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }


    /**
     * set system status bar color，如果调用了此方法，记得一定要调用removeStatusBarView方法
     *
     * @param activity
     * @param colorId
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarColor(Activity activity, int colorId) {
        int sdk_int = Build.VERSION.SDK_INT;
        if (sdk_int >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(colorId);
            return;
        }
        if (sdk_int >= Build.VERSION_CODES.KITKAT) {
            StatusBarUtil.setStatusBarTranslucent(activity);
            Window window = activity.getWindow();
            ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
            View statusBarView = new View(window.getContext());
            int statusBarHeight = getStatusBarHeight(window.getContext());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
            params.gravity = Gravity.TOP;
            statusBarView.setLayoutParams(params);
            statusBarView.setBackgroundColor(colorId);
            decorViewGroup.addView(statusBarView);
            if (mStatusBarViewList == null) {
                mStatusBarViewList = new ArrayList<>();
            }
            mStatusBarViewList.add(statusBarView);
        }
    }

    /**
     * 移除覆盖在状态栏上的View，调用了setStatusBarColor方法一定要在onDestroy调用此方法
     *
     * @param activity
     */
    public static void removeStatusBarView(Activity activity) {
        if (mStatusBarViewList != null && !mStatusBarViewList.isEmpty()) {
            Window window = activity.getWindow();
            ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
            for (View view : mStatusBarViewList) {
                decorViewGroup.removeView(view);
            }
        }
    }


    /**
     * get system status bar height
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * set System no status bar
     * must be before setContentView
     */
    public static void setNoSystemStatusBar(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 全透状态栏
     */
    public static void setStatusBarFullTransparent(Activity activity) {
        //21表示5.0
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //19表示4.4
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 半透明状态栏
     */
    public static void setHalfTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
