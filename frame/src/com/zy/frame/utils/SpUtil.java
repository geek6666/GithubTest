package com.zy.frame.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharePreferences操作工具类
 */
public class SpUtil {
    private static SharedPreferences sp;
    /**
     * 配置 SP文件名
     */
    private final static String SP_CONFIG = "config";
    /**
     * 登录APP SP文件名
     */
    public final static String SP_LOGIN = "login";

    public final static String SP_SELECT_CITY = "city";

    //默认的文件名，在使用getData(String key, Object defValue)有用
    private static String defaultSpName = SP_CONFIG;

    /**
     * SP key值 第一次使用该APP，可用来设置欢迎界面
     */
    public static final String KEY_FIRST_USE = "key_First_Use";

    public static final String KEY_LOCATION_LATITUDE = "key_location_Latitude";
    public static final String KEY_LOCATION_LONGITUDE = "key_location_Longitude";
    public static final String KEY_LOCATION_PROVINCE = "key_location_Province";
    public static final String KEY_LOCATION_CITY = "key_location_City";
    public static final String KEY_LOCATION_DISTRICT = "key_location_District";


    /**
     * 配置文件保存到sp中，默认的spname=config
     */
    public static void saveData(String key, Object value) {
        saveData(defaultSpName, key, value);
    }

    /**
     * 从配置文件中获取数据，默认的spname=config
     */
    public static Object getData(String key, Object defValue) {
        return getData(defaultSpName, key, defValue);
    }

    /**
     * 保存到SharedPreference中
     */
    public static void saveData(String spName, String key, Object value) {
        if (sp == null)
            sp = UIUtils.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        }
        if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        }
        if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        }
        if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        }
        if (value instanceof String) {
            edit.putString(key, (String) value);
        }
        edit.commit();
    }

    /**
     * 获取保存在sp中的值
     */
    public static Object getData(String spName, String key, Object defValue) {
        if (sp == null)
            sp = UIUtils.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        Object result = null;
        if (defValue instanceof Boolean) {
            result = sp.getBoolean(key, (Boolean) defValue);
        }
        if (defValue instanceof Float) {
            result = sp.getFloat(key, (Float) defValue);
        }
        if (defValue instanceof Integer) {
            result = sp.getInt(key, (Integer) defValue);
        }
        if (defValue instanceof Long) {
            result = sp.getLong(key, (Long) defValue);
        }
        if (defValue instanceof String) {
            result = sp.getString(key, (String) defValue);
        }
        return result;
    }

    public static final String KEY_USERID = "key_userID";
    public static final String KEY_SESSION = "key_session";
    public static final String KEY_PHONE_NUMBER = "key_phone_number";
    public static final String KEY_PASSWORD = "key_password";

    public static void saveLoginInfo(String key, String value) {
        saveData(SP_LOGIN, key, value);
    }

    public static String getLoginInfo(String key) {
        return (String) getData(SP_LOGIN, key, "");
    }


}
