package fr.ws.reader.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.androidadvance.topsnackbar.Prompt;
import com.androidadvance.topsnackbar.TSnackbar;

import fr.ws.reader.R;
import fr.ws.reader.app.Constants;
import fr.ws.reader.app.MainApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 普通工具类
 */
public class Utils {

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = MainApplication.app.getPackageManager();
            PackageInfo info = manager.getPackageInfo(MainApplication.app.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUToken(Context context) {
        return D.getInstance(context).getString(Constants.HEADER_U_TOKEN, "");
    }

    public static PackageInfo getPackageInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            return pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            L.e(e.getLocalizedMessage());
        }
        return new PackageInfo();
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        android.app.ActivityManager activityManager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (android.app.ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return "";
    }

    /**
     * dp值转化为px值
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static float dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dipValue * scale + 0.5f;
    }

    public static int dpToPx(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int spToPx(int sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    /**
     * 获取拼音的首字母（大写）
     *
     * @param pinyin
     * @return
     */
    public static String getFirstLetter(final String pinyin) {
        if (TextUtils.isEmpty(pinyin)) return "定位";
        String c = pinyin.substring(0, 1);
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c).matches()) {
            return c.toUpperCase();
        } else if ("0".equals(c)) {
            return "定位";
        } else if ("1".equals(c)) {
            return "热门";
        }
        return "定位";
    }

    /**
     * 生成0-5随机数
     *
     * @return 随机数
     */
    public static int getRandomInt() {
        int randomInt = 0;
        HashSet<Integer> integerHashSet = new HashSet<Integer>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            randomInt = random.nextInt(6);
            if (!integerHashSet.contains(randomInt)) {
                integerHashSet.add(randomInt);
            }
        }
        return randomInt;
    }

    /**
     * 跳转activity
     *
     * @param context  上下文
     * @param activity 跳转activity
     */
    public static void startActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    /**
     * 跳转activity
     *
     * @param context  上下文
     * @param activity 跳转activity
     * @param bundle   数据
     */
    public static void startActivity(Context context, Class<?> activity, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 获取过去7天的日期方法
     *
     * @return
     */
    public static ArrayList<String> getSevenPastDate() {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (i == 0)
                pastDaysList.add("今天");
            else
                pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    private static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        return format.format(today);
    }

    /**
     * 清除用户所有信息（个人信息，本地存储的信息）
     *
     * @param context 上下文
     */
    public static void clearInfo(Context context) {
        MainApplication.app.setAccount(null);
        MainApplication.app.setUser(null);
        D.getInstance(context).putString(Constants.USER_INFO, "");  //清除个人信息数据
        D.getInstance(context).putString(Constants.ACCOUNT_INFO, "");   //清除个人账号信息
        D.getInstance(context).putString("password", "");  //清除个人信息数据
        DatabaseHandler dh =new DatabaseHandler(context);
        dh.deleteDB();
    }

    /**
     * 时间换算 剩余多少分钟 小时 秒
     *
     * @param restSecond
     * @return
     */
    public static String getTimeCounter(int restSecond) {
        if (restSecond / 3600 > 0) {
            return (restSecond / 3600) + "小时";
        } else {
            if (restSecond / 60 > 0) {
                return (restSecond / 60) + "分钟";
            } else {
                if (restSecond < 0) {
                    return 0 + "秒";
                } else
                    return restSecond + "秒";
            }
        }
    }

    public static String getVolUnit(float num) {
        int e = (int) Math.floor(Math.log10(num));
        if (e >= 8) {
            return "亿手";
        } else if (e >= 4) {
            return "万手";
        } else {
            return "手";
        }
    }

    /**
     * 清除重复的数据
     *
     * @param list
     * @return
     */
    public static List<String> removeDuplicate(List<String> list) {
        Set set = new LinkedHashSet<String>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    /**
     * 格式化一下 价格
     *
     * @param price 价格
     * @return 价格
     */
    public static String getPrice(String price) {
        DecimalFormat df = new DecimalFormat("0.00");
        String priceFirst = df.format(Double.parseDouble(price));
        if (priceFirst.isEmpty())
            return "0.00€";
        if (priceFirst.contains(".")) {
            priceFirst = priceFirst.replace(".", ",");
            return priceFirst + "€";
        } else {
            return priceFirst + "€";
        }
    }

    /**
     * 小于100000的不转换，大于或等于100000的转换为10万，以此类推，110000转为11万，112000为11.2万
     *
     * @author inferno
     */

    public static String getPriceWithWan(String price) {
        DecimalFormat df = new DecimalFormat("0");
        double doublePrice = Double.parseDouble(price);
        if (doublePrice < 10000) {
            if (price.endsWith(".00") || price.endsWith(".0")) {
                return "¥" + df.format(Double.parseDouble(price));
            } else {
                return "¥" + Double.parseDouble(price);
            }
        } else {
            double n = doublePrice / 10000;
            String strN = n + "";
            if (strN.endsWith(".00") || strN.endsWith(".0")) {
                strN = df.format(Double.parseDouble(strN));
            } else {
                strN = Double.parseDouble(strN) + "";
            }
            return "¥" + strN + "万";
        }
    }

    /**
     * 1，2，3随机数获取
     *
     * @return 1，2，3
     */
    public static int getRandom123() {
        Random rd = new Random(); //创建一个Random类对象实例
        return rd.nextInt(3) + 1;
    }

    /**
     * 系统分享 朋友圈转售
     *
     * @param context 上下文
     * @param uris    图片uri数组
     * @param content 内容
     */
    public static void shareToWechatCircle(Context context, ArrayList<Uri> uris, String content) {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        intent.putExtra("Kdescription", content);
        context.startActivity(intent);
    }

    /**
     * 获取所有物流（根据本地json文件）
     *
     * @param context
     * @return
     */
    public static String getCountries(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.countrylist)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * SnackBar提示
     *
     * @param view
     * @param msg
     */
    public static void showSnackBar(View view, String msg, int type) {
        if (view != null) {
            TSnackbar tSnackbar = TSnackbar.make(view, msg, TSnackbar.LENGTH_SHORT);
            switch (type) {
                //成功提示
                case 1:
                    tSnackbar.setPromptThemBackground(Prompt.SUCCESS);
                    tSnackbar.show();
                    break;
                //失败提示
                case 2:
                    tSnackbar.setPromptThemBackground(Prompt.ERROR);
                    tSnackbar.show();
                    break;
                //警告提示
                case 3:
                    tSnackbar.setPromptThemBackground(Prompt.WARNING);
                    tSnackbar.show();
                    break;
                default:
                    break;
            }
        }
    }
}
