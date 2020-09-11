package com.lyw.commonutils.weight;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.lyw.commonutils.R;
import java.util.Locale;

/**
 * 功能描述:设置item边距
 * Created on 2020/6/29.
 * @author lyw
 */
public class ThirdLoginItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private int mItemOffset;

    public ThirdLoginItemDecoration(Context context, int itemOffset) {
        mContext = context;
        if (itemOffset > 0) {
            mItemOffset = itemOffset;
        } else {
            mItemOffset = mContext.getResources().getDimensionPixelSize(R.dimen.login_item_padding_30);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int size = parent.getChildCount();
        if (size == 1) {
            return;
        }
        boolean rtl = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_RTL;
        for (int i = 0; i < size; i++) {
            View childView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(childView);

            if (position == 0) {
                if (rtl) {
                    outRect.left = mItemOffset;
                    outRect.right = 0;
                } else {
                    outRect.left = 0;
                    outRect.right = mItemOffset;
                }
            } else if (position == size - 1) {
                if (rtl) {
                    outRect.left = 0;
                    outRect.right = mItemOffset;
                } else {
                    outRect.left = mItemOffset;
                    outRect.right = 0;
                }
            } else {
                outRect.left = mItemOffset;
                outRect.right = mContext.getResources().getDimensionPixelSize(R.dimen.login_item_padding_30);
            }
        }
    }
}
