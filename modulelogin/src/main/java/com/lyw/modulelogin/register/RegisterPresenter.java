package com.lyw.modulelogin.register;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.lyw.commonutils.utils.RegularUtil;
import com.lyw.modulelogin.R;
import com.lyw.modulelogin.been.CountryInfo;
import com.lyw.modulemvp.base.BasePresenter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:
 * Created on 2020/7/2.
 *
 * @author lyw
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.Model,RegisterContract.View> implements RegisterContract.Presenter {
    private Context mContext;
    //支持手机号
    private boolean mIsSupportMobile = true;
    //支持邮箱
    private boolean mIsSupportEmail = true;
    /**
     * 地区编号
     */
    private List<CountryInfo> mCountryInfoList = new ArrayList<>();

    public RegisterPresenter(Context context) {
        mContext = context;
    }

    @Override
    protected RegisterContract.Model createModule() {
        return new RegisterModel();
    }

    @Override
    public void start() {
        initPhoneOfArea();
    }

    @Override
    public void checkAccount(boolean mIsCheckNumber,String userAccount) {
        if (RegularUtil.isContainChinese(userAccount)) {
            getView().handleCheckResult("不能含有中文");
            return ;
        }

        if (RegularUtil.isContainSpace(userAccount) || userAccount.trim().length() == 0) {
            getView().handleCheckResult("不能含有空格");
            return ;
        }
//        if (mIsSupportMobile && mIsSupportEmail) {
//            if (!RegularUtil.checkMailFormat(userAccount)) {
//                if (RegularUtil.isNumberPure(userAccount)) {
//                    getView().showUserAccountEdt(handlePhoneNumber(userAccount));
//                    if (!mIsCheckNumber) {
//                        getView().setIsCheckNumber(true);
//                        getView().handleCheckResult("再次点击确认,已确保手机地区编号正确");
//                        return;
//                    }
//                } else {
//                    if (RegularUtil.isAreaPhoneNumber(userAccount)) {
//                        if (!mIsCheckNumber) {
//                            if (!mIsCheckNumber) {
//                                getView().setIsCheckNumber(true);
//                                getView().handleCheckResult("再次点击确认,已确保手机地区编号正确");
//                                return;
//                            }
//                        }
//                    } else {
//                        if (!RegularUtil.checkMailFormat(userAccount)) {
//                            getView().handleCheckResult("邮箱格式输入不对");
//                            return;
//                        }
//                    }
//                }
//            }
//        } else if (mIsSupportMobile) {
//            if (RegularUtil.isNumberPure(userAccount)) {
//                getView().showUserAccountEdt(handlePhoneNumber(userAccount));
//                if (!mIsCheckNumber) {
//                    getView().setIsCheckNumber(true);
//                    getView().handleCheckResult("再次点击确认,已确保手机地区编号正确");
//                    return;
//                }
//            } else if (RegularUtil.isAreaPhoneNumber(userAccount)) {
//                if (!mIsCheckNumber) {
//                    getView().setIsCheckNumber(true);
//                    getView().handleCheckResult("再次点击确认,已确保手机地区编号正确");
//                    return;
//                }
//            }
//            return;
//        } else if (mIsSupportEmail) {
//            if (!RegularUtil.checkMailFormat(userAccount)) {
//                getView().handleCheckResult("邮箱格式输入不对");
//                return;
//            }
//        }

        getView().isShowLoading(true);
        getView().goinVerifyCode(userAccount);
    }


    /**
     * 处理手机号
     * @param number
     * @return
     */
    private String handlePhoneNumber(String number) {
        try {
            for (int i = 0; i < mCountryInfoList.size(); i++) {
                if (mCountryInfoList.get(i).getId().equals(mContext.getResources().getConfiguration().locale.getCountry())) {
                    return "" + mCountryInfoList.get(i).getNumber() + "" + number;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "+86" + number;
    }

    /**
     * 加载地区编号
     */
    private void initPhoneOfArea() {
        XmlResourceParser xrp = mContext.getResources().getXml(R.xml.country);
        try {
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    if (xrp.getName().equals("country_info")) {
                        CountryInfo countryInfo = new CountryInfo();
                        String number = xrp.getAttributeValue(1);
                        number = "+" + number.substring(0, number.indexOf("."));
                        countryInfo.setId(xrp.getAttributeValue(0));
                        countryInfo.setNumber(number);
                        mCountryInfoList.add(countryInfo);
                    }
                }
                xrp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
