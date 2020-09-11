package com.lyw.commonutils.utils;

import android.text.TextUtils;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lyw on 2017/4/24.
 * <p>
 * 正则的检查
 */

public class RegularUtil {

    /**
     * 检查字符串是否符合某个正则
     *
     * @param s
     * @param rex
     * @return
     */
    public static boolean matchRex(String s, String rex) {
        Pattern p = Pattern.compile(rex);
        Matcher m = p.matcher(s);
        return m.matches();
    }


    /**
     * 是否是手机号
     * <p>
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、181、189
     *
     * @param txt
     * @return
     */
    public static boolean isMobilePhoneNumber(String txt) {
//        return matchRex(txt, "^[0-9]*$");
        return matchRex(txt, "^(1)\\d{10}$") || matchRex(txt, "^(\\+)?[0-9]*$");
        //return matchRex(txt, "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8}$");
    }


    /**
     * 是否加"+"号的手机号
     *
     * @param txt
     * @return
     */
    public static boolean isAreaPhoneNumber(String txt) {
        try {
            Log.i("isAreaPhoneNumber", "isAreaPhoneNumber: ------->" + txt.substring(0, 1) + "---" + txt.substring(1));
            if ("+".equals(txt.substring(0, 1)) && isNumberPure(txt.substring(1))) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
//        return matchRex(txt, "^(\\+)?[0-9]*$");
    }

    /**
     * 检测是否有中文字符
     *
     * @param txt
     * @return
     */
    public static boolean isContainChinese(String txt) {
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(txt);
        return m.find();
    }

    /**
     * 检测是否有全角字符
     *
     * @param txt
     * @return
     */
    public static boolean isContainAngle(String txt) {
        Pattern p = Pattern.compile("[\\uff00-\\uffff]");
        Matcher m = p.matcher(txt);
        return m.find();
    }

    /**
     * 检查邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean checkMailFormat(String email) {
        Pattern p = Pattern.compile("^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * 检查是否含有空格
     *
     * @param text
     * @return
     */
    public static boolean isContainSpace(String text) {
        Pattern p = Pattern.compile("[\\u0020]");
        Matcher m = p.matcher(text);
        return m.find();
    }

    /**
     * 检查是否含有英文字母
     *
     * @param text
     * @return
     */
    public static boolean isContainEnglish(String text) {
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(text);
        return m.matches();
    }


    /**
     * 检查预置位参数是否0~255
     *
     * @param text
     * @return
     */
    public static boolean isContainPresetParameter(String text) {
//        Pattern p = Pattern.compile("^(\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5])");
//        Matcher m = p.matcher(text);
//        return m.matches();
        int pos = -1;
        try {
            pos = Integer.parseInt(text);
            return pos >= 0 && pos <= 255;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 检查是否网址
     *
     * @param url
     * @return
     */
    public static boolean isWebsiteUrl(String url) {
        //        "((\\w)+[.])\n" +
        //                "{1,}(net|com|cn|org|cc|tv|[0-9]{1,3})(((\\/[\\~]*|\\\\[\\~]*)(\\w)+)|[.]\n" +
        //                "(\\w)+)*(((([?](\\w)+){1}[=]*))*((\\w)+){1}([\\&](\\w)+[\\=](\\w)\n" +
        //                "+)*)"
        return matchRex(url, "((\\w)+[.]){1,}(net|com|cn|org|cc|tv|info|[0-9]{1,3})");
    }


    public static boolean isLetterPure(String data) {
        return matchRex(data, "^[a-zA-Z]*$");
    }

    /**
     * 纯数字
     *
     * @return
     */
    public static boolean isNumberPure(String data) {
        return matchRex(data, "^[0-9]*$");
    }


    public static boolean isOnlyContainNumberAndLetter(String data) {
        return matchRex(data, "^[0-9a-zA-z]*$");
    }


    /**
     * 是否包含不支持的特殊字符&
     *
     * @param data
     * @return
     */
    public static boolean isContainUnsupportedSpecialChar(String data) {
        if (TextUtils.isEmpty(data)) {
            return false;
        }
        return matchRex(data, "\\u0026+");
    }

    /**
     * 匹配设备密码格式是否正确
     *
     * @param data
     * @return
     */
    public static boolean isDevPwdMatchCorrect(String data) {
        if (TextUtils.isEmpty(data)) {
            return true;
        }
        // \u0026 -> &
        return matchRex(data, "^[\\u0020-\\u0025\\u0027-\\u007E]*$");
    }


    /**
     * 是否支持URL编码
     *
     * @param data
     * @return
     */
    public static boolean isURLEncodeSupport(String data) {
        try {
            URLEncoder.encode(data, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 是否包含特殊字符
     *
     * @param data
     * @return
     */
    public static boolean isContainSpecialChar(String data) {
        if (!TextUtils.isEmpty(data)) {
            for (int i = 0; i < data.length(); i++) {
                char c = data.charAt(i);
                if (c < 32 || c > 122) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean isIPAdress(String data) {
        return matchRex(data, "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$");
    }

    /**
     * @param start
     * @return
     */
    public static boolean binaryValue(int num, int start, boolean toRight) {
        if (toRight) {
            return (num >> start & 0x01) == 1;
        }
        return (num << start & 0x01) == 1;

    }

    public static boolean binaryValue(int num, int start) {
        return binaryValue(num, start, true);
    }

}
