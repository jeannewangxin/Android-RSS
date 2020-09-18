package fr.ws.reader.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 常用字符串工具类
 */
public class StringUtils {

    // 获得文件类型
    public static String getFileType(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }

    // 手机号验证
    public static boolean isPhone(String phone) {
        Pattern p;
        Matcher m;
        boolean b;
        p = Pattern.compile("^((13[0-9])|(16[0-9])|(19[0-9])|(17[0-9])|(147)|(15[0-9])|(18[0-9]))\\d{8}$");
        m = p.matcher(phone);
        b = m.matches();
        return b;
    }

    // 隐藏手机号中间4位
    public static String hidePhone(String phone) {
        String s = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return s;
    }

    // 隐藏email（只保留第一位和最后一位）
    public static String hideEmail(String email) {
        String s = email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
        return s;
    }

    // 邮箱号验证
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // 判断是否是汉字
    public static boolean isChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    // 是否是身份证
    public static boolean isIdcard(String idno) {
        String regEx = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(idno);
        return m.matches();
    }

    // 是否是数字
    public static boolean isNumber(String str) {
        String regEx = ".*\\d+.*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    // 字母加数字的验证
    public static boolean isCharAndNum(String str) {
        String regEx = "^[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();

    }

    //拼接逗号
    public static String listToString2(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    } //拼接逗号

    public static String listToString3(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + "|");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    public static boolean isData(String str) {
        String pattern = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})" +
                "-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|" +
                "(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|" +
                "((0[48]|[2468][048]|[3579][26])00))-02-29)\n)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 判断是否是银行卡号
     *
     * @param cardId
     * @return
     */
    public static boolean isBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;

    }

    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 截取银行卡号的最后4位数!!!!!!!!!!!!!
     *
     * @param bankCard
     * @return
     */
    public static String cutDownBankCard(String bankCard) {
        String result = bankCard.substring(bankCard.length() - 4, bankCard.length());
        return result;
    }

    public static String floatToString(float f) {
        String s = f + "";
        if (s.endsWith(".0")) {
            s = s.substring(0, s.length() - 2);
        }
        return s;
    }

    // 生成随机字符串
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 获取url 指定name的value
     *
     * @param url  url地址
     * @param name 对应的名称
     * @return 值
     */
    public static String getValueByName(String url, String name) {
        String result = "";
        int index = url.indexOf("?");
        String temp = url.substring(index + 1);
        String[] keyValue = temp.split("&");
        for (String str : keyValue) {
            if (str.contains(name)) {
                result = str.replace(name + "=", "");
                break;
            }
        }
        return result;
    }

    //map转成json字符串
    public static String map2json(Map<String, String> map) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(map);
    }

    //截取手机号中间4位用"*"代替
    public static String cutDownPhoneNum(String phoneNum) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(phoneNum) && phoneNum.length() > 6) {
            for (int i = 0; i < phoneNum.length(); i++) {
                char c = phoneNum.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    //将身份证号中间用*代替 2****************8
    public static String cutDownIdCard(String idCard) {
        StringBuilder sb = new StringBuilder();
        if (idCard != null) {
            if (!TextUtils.isEmpty(idCard) && idCard.length() > 17) {
                for (int i = 0; i < idCard.length(); i++) {
                    char c = idCard.charAt(i);
                    if (i >= 1 && i <= 16) {
                        sb.append('*');
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 限制汉字的输入(登录密码)
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        String regEx = "[\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 将图片url用“|”隔开
     *
     * @param stringList 图片url数组
     * @return
     */
    public static String getStringListBySymbol(List<String> stringList) {
        String b = "";
        for (int i = 0; i < stringList.size(); i++) {
            String a = "";
            if (i == stringList.size() - 1) {
                a = stringList.get(i);
            } else {
                a = stringList.get(i) + "|";
            }
            b = b + a;
        }
        return b;
    }

    /**
     * 分割手机号
     *
     * @param phone 手机号码
     * @return
     */
    public static String dividerPhone(String phone) {
        if (!TextUtils.isEmpty(phone) && phone.length() > 10) {
            String start = phone.substring(0, 3);
            String middle = phone.substring(3, 7);
            String end = phone.substring(7, 11);
            return start + " " + middle + " " + end;
        }
        return "";
    }

    /**
     * 用","拼接字符串
     *
     * @param strings 字符串的集合
     * @return 字符串
     */
    public static String splitString(String[] strings) {
        String result = "";
        for (String id : strings) {
            if (id != null) {
                result += id + ",";
            }
        }
        return result.substring(0, result.length() - 1);
    }
}
