package fr.ws.reader.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharePreference本地存储工具类
 */
public class D {

    private Context context;
    private final String dbName = "userinfo";
    private static D instance;

    private D(Context context) {
        this.context = context;
    }

    public static D getInstance(Context context) {
        if (instance == null)
            instance = new D(context);
        return instance;
    }

    public void putString(String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defValue) {
        SharedPreferences preferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
        return preferences.getString(key, defValue);
    }

    public void putInt(String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int defValue) {
        SharedPreferences preferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
        return preferences.getInt(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        SharedPreferences preferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defValue);
    }

    public void putLong(String key, long value) {
        SharedPreferences preferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(String key, long defValue) {
        SharedPreferences preferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
        return preferences.getLong(key, defValue);
    }

    /**
     * 设置SharedPreferences
     *
     * @param context      上下文
     * @param fileName     文件名
     * @param paramName    参数名
     * @param paramContent 参数值
     */
    public static void setParams(Context context, String fileName, String paramName, String paramContent) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(paramName, paramContent);
        editor.commit();
        editor.clear();
    }

    /**
     * 获取SharedPreferences
     *
     * @param context   上下文
     * @param fileName  文件名
     * @param paramName 参数名
     * @return 参数值
     */
    public static String getParams(Context context, String fileName, String paramName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(paramName, "");
    }

    /**
     * 是否包含
     *
     * @param context
     * @param fileName
     * @param paramName
     * @return
     */
    public static boolean isContainsParams(Context context, String fileName, String paramName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.contains(paramName);
    }


}
