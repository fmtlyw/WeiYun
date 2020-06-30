package com.lyw.modulemvp.dialog;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.lyw.commonutils.utils.AnimatorUtil;
import com.lyw.commonutils.utils.DisplayUtil;
import com.lyw.modulemvp.R;


/**
 * 加载框
 * Created by LYW on 2019-01-07.
 */
public class LoadingLayout extends FrameLayout {

    private Context context;

    private ObjectAnimator rotateAnimator;

    private ImageView loadingView;

    public LoadingLayout(@NonNull Context context) {
        this(context, null);
    }

    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {
            updateView();
            rotateAnimator.start();
        } else {
            rotateAnimator.cancel();
        }
    }

    public void setTopOffset(int offset) {
        ViewGroup.LayoutParams lp = getLayoutParams();
        if (lp instanceof LayoutParams) {
            LayoutParams layoutParams = (LayoutParams) lp;
            layoutParams.topMargin = offset;

        } else if (lp instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) lp;
            layoutParams.topMargin = offset;

        }  else if (lp instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) lp;
            layoutParams.topMargin = offset;
        }
    }

    public void setLoadingResource(int imgId) {
        Glide.with(context).load(imgId).into(loadingView);
    }

    private void init() {
        super.setVisibility(GONE);
        initView();
    }

    private void initView() {
        int viewSize = (int) DisplayUtil.dip2px(context, 44);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(viewSize, viewSize);
        ((LayoutParams) layoutParams).gravity = Gravity.CENTER;
        ImageView imageView = new ImageView(context);
        imageView.setMaxHeight(viewSize);
        imageView.setMaxWidth(viewSize);
        imageView.setVisibility(VISIBLE);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Glide.with(context).load(R.mipmap.icon_loading).into(imageView);

        loadingView = imageView;
        rotateAnimator = AnimatorUtil.getRotateTransactionAni(0, 359f, 1200, imageView);
        addView(imageView);
    }

    private void updateView() {
        int margin = 0;
        ViewGroup.LayoutParams lp = getLayoutParams();
        if (lp instanceof LayoutParams) {
            LayoutParams layoutParams = (LayoutParams) lp;
            margin = layoutParams.topMargin;

        } else if (lp instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) lp;
            margin = layoutParams.topMargin;

        } else if (lp instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) lp;
            margin = layoutParams.topMargin;
        }
        LayoutParams layoutParams = (LayoutParams) loadingView.getLayoutParams();
        layoutParams.bottomMargin = margin;
    }
}
