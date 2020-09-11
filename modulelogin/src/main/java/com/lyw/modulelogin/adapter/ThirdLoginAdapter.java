package com.lyw.modulelogin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyw.modulelogin.R;
import com.lyw.modulelogin.R2;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThirdLoginAdapter extends RecyclerView.Adapter {

    public static final int THIRD_LOGIN_WECHAT = 0; // 微信登录
    public static final int THIRD_LOGIN_GOOGLE = 2; // 谷歌登录
    public static final int THIRD_LOGIN_LINE = 3; // Line登录

    private List<ImageView> mDataList;
    private Context mContext;
    private ThirdItemIvListener mListener;

    public ThirdLoginAdapter(Context context) {
        mContext = context;
        mDataList = new ArrayList<>();
    }

    public void addItem(int loginType) {
        ImageView imageView = new ImageView(mContext);
        imageView.setTag(loginType);
        mDataList.add(imageView);
    }

    public void setDataList(List<ImageView> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_login_item_third_iv, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        ImageView imageView = mDataList.get(position);
        Object tag = imageView.getTag();
        if (tag == null) {
            tag = position;
        }
        int loginType = (int) tag;
        if (loginType == THIRD_LOGIN_WECHAT) {
            viewHolder.itemIv.setImageResource(R.mipmap.icon_login_wechat_2);
            viewHolder.item_tv.setText("微信登录");
        } else if (loginType == THIRD_LOGIN_GOOGLE){
            viewHolder.itemIv.setImageResource(R.mipmap.icon_login_google);
            viewHolder.item_tv.setText("Google登录");
        } else if (loginType == THIRD_LOGIN_LINE){
            viewHolder.itemIv.setImageResource(R.mipmap.icon_line_log_in);
            // FIXME: 2020/6/16 国际化
            viewHolder.item_tv.setText("Line 登录");
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setThirdItemIvListener(ThirdItemIvListener listener) {
        this.mListener = listener;
    }

    public interface ThirdItemIvListener {
        void onClickItemIv(View view, int position, int loginType);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.item_iv)
        ImageView itemIv;

        @BindView(R2.id.item_tv)
        TextView item_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R2.id.item_ll)
        void onClickItemLl(View view) {
            int position = getAdapterPosition();
            if (position < 0) {
                return;
            }
            Object tag = mDataList.get(position).getTag();
            if (tag == null) {
                tag = position;
            }
            if (mListener != null) {
                mListener.onClickItemIv(view, position, (int) tag);
            }
        }
    }

}
