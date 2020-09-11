package com.lyw.commonutils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ProgressBar;
import com.lyw.commonutils.R;

/**
*  加载对话框
*  @author lyw
*  @time 2020/7/9 上午 10:54
*/
public class LoadingDialog extends Dialog {
    private ProgressBar mProgressBarBlack;
    private ProgressBar mProgressBarWhite;
    private boolean mHandleTouch = true;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.common_utils_loading_dialog);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * 默认显示黑色的loading
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_utils_dialog_loading_layout);


        mProgressBarBlack = findViewById(R.id.dialog_loading_black_pb);
        mProgressBarWhite = findViewById(R.id.dialog_loading_white_pb);

        setCanceledOnTouchOutside(false);
    }

    public void setBlackProVisibility(int state) {
        mProgressBarBlack.setVisibility(state);

    }

    public void setWhiteProVisibility(int state) {
        mProgressBarWhite.setVisibility(state);
    }

    @Override
    protected void onStart() {
        super.onStart();
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        if (!mHandleTouch){
            params.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        }
        this.getWindow().setAttributes(params);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (!mHandleTouch){
            return false;
        }
        return super.onTouchEvent(event);
    }


    public void setHandleTouch(boolean handleTouch) {
        mHandleTouch = handleTouch;
    }
}
