package com.lyw.commonutils.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LYW on 2017/5/3.
 */

public class AnimatorUtil {

    /**
     * 高度位移动画
     *
     * @param start
     * @param end
     * @param view
     * @return
     */
    public static ValueAnimator getHeightTransactionAni(int start, int end, final View view) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = value;
                view.setLayoutParams(params);
            }
        });
        animator.start();
        return animator;
    }

    public static ValueAnimator getHeightTransactionAni(int start, int end, int time, final View view) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(time);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = value;
                view.setLayoutParams(params);
            }
        });
        return animator;
    }

    public static ValueAnimator getHeightTransactionAni(int start, int end, int time, final List<View> views) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(time);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                for (View view : views) {
                    ViewGroup.LayoutParams params = view.getLayoutParams();
                    params.height = value;
                    view.setLayoutParams(params);
                }

            }
        });
        return animator;
    }

    public static ValueAnimator getWidthTransctionAni(Context context, int start, int end, int time, final View view) {
        final ValueAnimator animator = ValueAnimator.ofInt(start, end);
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        animator.setDuration(time);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.width = value;
                view.setLayoutParams(params);
            }
        });
        return animator;
    }

    /**
     * 旋转动画
     *
     * @param start
     * @param end
     * @param time
     * @param view
     * @return
     */
    public static ObjectAnimator getRotateTransactionAni(float start, float end, int time, final View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", start, end);
        animator.setRepeatCount(-1);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setDuration(time);
        return animator;
    }

}
