package utils;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.weight.CustomToast;

/**
 * 字符串工具类
 * Created by huyunke on 2018/4/4.
 */

public class StringUtils {
    /**
     * 验证电话号码
     *
     * @param
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isTelephone(String str) {
        String regex = "^1[34578]\\d{9}$";
        return match(regex, str);
    }

    /**
     * 将电话号码第4位开始4个数字变成*号
     *
     * @param phoneNum
     * @return
     */
    public static String getTelephoneForStar(String phoneNum) {
        return phoneNum.replaceAll("(\\d{3})\\d{4}(\\d{0,3})", "$1****$2");
    }

    /*
                 xxxxxx yyyy MM dd 375 0     十八位

                 地区：[1-9]\d{5}
                 年的前两位：(19|20)            1900-20xx 暂时只能是19和20开头
                 年的后两位：\d{2}
                 月份：(0[1-9]|1[0-2])
                 天数：([0-2][1-9]|[1-3]0|31)          没判断闰年29

                 三位顺序码：\d{3}
                 校验码：[0-9Xx]

                 十八位："(^[1-9]\\d{5}(19|20)\\d{2}(0[1-9]|1[0-2])([0-2][1-9]|[1-3]0|31)\\d{3}[0-9Xx]$)"
                 */
    public static boolean isIDCard(String str) {
        String regex = "(^[1-9]\\d{5}(19|20)\\d{2}(0[1-9]|1[0-2])([0-2][1-9]|[1-3]0|31)\\d{3}[0-9Xx]$)";
        return match(regex, str);
    }

    /**
     * 将时间戳转为代表"距现在多久之前"的字符串
     *
     * @param timeStr
     * @param isToNow
     * @return
     */
    public static String getStandardDates(Long timeStr, boolean isToNow) {
        long time;
        if (isToNow) time = System.currentTimeMillis() - timeStr;
        else time = timeStr - System.currentTimeMillis();
        return convertStandardDates(time) + "";
    }

    /**
     * 将时间搓转成时间
     *
     * @param type：时间样式 比如：yyyy-MM-dd
     * @return
     */
    public static String timeStyleByType(String type) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(type);
        String time = format.format(date);
        return time;
    }

    /**
     * 将时间转换为时间戳
     * type:"yyyy-MM-dd HH:mm:ss"
     */
    public static long dateToStamp(String s, String type) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
            Date date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            return ts;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 将文字金额转化成数字金额
     *
     * @param str
     * @return
     */
    public static float moneyStrToInt(String str) {
        float money = 0;
        if (TextUtils.isEmpty(str)) return money;
        if (str.contains("万元")) {
            str = str.replaceAll("万元", "");
            money = Float.valueOf(str) * 10000;
        } else if (str.contains("千元")) {
            str = str.replaceAll("元", "");
            money = Float.valueOf(str) * 1000;
        } else if (str.contains("百元")) {
            str = str.replaceAll("元", "");
            money = Float.valueOf(str) * 100;
        } else if (str.contains("元")) {
            str = str.replaceAll("元", "");
            money = Float.valueOf(str);
        }
        return money;
    }

    /**
     * 将数字金额转化成文字金额
     *
     * @param
     * @return
     */
    public static String moneyIntToStr(float num) {
        String money = "";
        if (num >= 10000) {
            num = num / 10000;
            money = num + "万元";
        } else {
            money = num + "元";
        }

        return money;
    }

    /**
     * 检查TextView是否为空
     *
     * @param w
     * @param displayStr
     * @return
     */
    public static boolean isEmpty(TextView w, String displayStr) {
        if (TextUtils.isEmpty(w.getText().toString().trim())) {
            showErrorMsg(w, displayStr);
            return true;
        }
        return false;
    }

    /**
     * 将字符串类型的时间转换成Date
     * @param dateStr
     * @param dateFormatStr
     * @return
     */
    public static Date formatDateByString(String dateStr, String dateFormatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**************************************************************************************************************************************************************/
    private static void showErrorMsg(TextView w, String displayStr) {
        if (true) {
            showPop(w.getContext(), displayStr);
        } else {
            CharSequence errorText = Html.fromHtml("<font color='blue'>" + displayStr + "</font>");
            w.setError(errorText);
        }
    }

    private static void showPop(Context context, String displayStr) {
        // Toast.makeText(context, displayStr, 0).show();
        CustomToast.showToast(context, displayStr, 2000);
    }

    /**
     * 将时间戳转为代表"距现在多久之前"的字符串
     *
     * @return
     */
    private static String convertStandardDates(long time) {
        StringBuffer sb = new StringBuffer();
        long mill = (long) Math.floor(time / 1000.0f);// 秒前
        long minute = (long) Math.floor(time / 60 / 1000.0f);// 分钟前
        long hour = (long) Math.floor(time / 60 / 60 / 1000.0f);// 小时
        long day = (long) Math.floor(time / 24 / 60 / 60 / 1000.0f);// 天前
        long month = (long) Math.floor(time / 24 / 60 / 60 / 30 / 1000.0f);// 月前
        long year = (long) Math.floor(time / 24 / 60 / 60 / 30 / 12 / 1000.0f);// 年前
        if (year > 0) {
            sb.append(year + "年");
        } else if (month > 0) {
            sb.append(month + "个月");
        } else if (day > 0) {
            sb.append(day + "天");
        }
        if (TextUtils.isEmpty(sb + "")) {
            int h = (int) (hour - (24 * day));
            if (h > 0) sb.append(h + "小时");
            int mu = (int) (minute - (60 * hour));
            if (mu > 0) sb.append(mu + "分钟");
            if (TextUtils.isEmpty(sb + "")) {
                if (mill == 0) {
                    sb.append("刚刚");
                } else sb.append(mill + "秒");
            }
        }
        return sb.toString();
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
