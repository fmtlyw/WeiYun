package com.lyw.modulelogin.login;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.lyw.commonutils.utils.StatusBarUtil;
import com.lyw.commonutils.weight.ThirdLoginItemDecoration;
import com.lyw.modulelogin.R;
import com.lyw.modulelogin.R2;
import com.lyw.modulelogin.adapter.ThirdLoginAdapter;
import com.lyw.modulelogin.been.UserInfo;
import com.lyw.modulemvp.base.BaseMvpActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 功能描述:
 * Created on 2020/6/29.
 * @author lyw
 */
@Route("com.lyw.modulelogin.login.LoginActivity")
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View,ThirdLoginAdapter.ThirdItemIvListener{
    @BindView(R2.id.register_ll)
    LinearLayout mRegisterll;
    @BindView(R2.id.third_login_recycler_view)
    RecyclerView mThirdRecyclerView;

    private ThirdLoginAdapter mAdapter;

    @Override
    protected void init() {
        super.init();
        //全屏
        StatusBarUtil.setNoSystemStatusBar(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        //第三方登录
        mAdapter = new ThirdLoginAdapter(this);
        mAdapter.setThirdItemIvListener(this);
        mAdapter.addItem(0);
        mAdapter.addItem(2);
        mAdapter.addItem(3);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mThirdRecyclerView.setAdapter(mAdapter);
        mThirdRecyclerView.setLayoutManager(lm);
        //设置边距
        int itemOffset = getResources().getDimensionPixelSize(R.dimen.login_item_padding_70);
        if (mAdapter.getItemCount() >= 3) {
            itemOffset = getResources().getDimensionPixelSize(R.dimen.login_item_padding_30);
        }
        mThirdRecyclerView.addItemDecoration(new ThirdLoginItemDecoration(this, itemOffset));
    }

    @Override
    public UserInfo getUserInfo() {
        return null;
    }

    @Override
    public void loginSuccess(UserInfo userInfo) {

    }

    @OnClick(R2.id.register_ll)
    void onClickRegister(View view) {
//        UserInfo info = new UserInfo();
//        info.setUserName("威哥");
//        info.setPassword("123456");
//        info.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e==null){
//                    Toast.makeText(LoginActivity.this,"添加数据成功，返回objectId为："+s,Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(LoginActivity.this,"创建数据失败："+ e.getMessage(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        Router.build("com.lyw.modulelogin.register.RegisterActivity").go(this);
    }

    @OnClick(R2.id.confirm_btn)
    void onClickConfirmBtn(View view){
        Router.build("com.lyw.modulemain.main.MainActivity").go(this);
    }

    @Override
    public void onClickItemIv(View view, int position, int loginType) {
        if (loginType == ThirdLoginAdapter.THIRD_LOGIN_WECHAT) {
            Toast.makeText(this,"微信登录",Toast.LENGTH_SHORT).show();
        }else if (loginType == ThirdLoginAdapter.THIRD_LOGIN_GOOGLE) {
            Toast.makeText(this,"Google登录",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Line登录",Toast.LENGTH_SHORT).show();
        }
    }
}
