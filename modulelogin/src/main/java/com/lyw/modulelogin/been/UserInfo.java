package com.lyw.modulelogin.been;

import cn.bmob.v3.BmobObject;

/**
 * 功能描述:用户
 * Created on 2020/6/30.
 * @author lyw
 */
public class UserInfo extends BmobObject {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
