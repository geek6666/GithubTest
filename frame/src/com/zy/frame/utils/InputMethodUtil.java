package com.zy.frame.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Administrator on 2015/1/15.
 */
public class InputMethodUtil {

    /**
     * 隐藏输入法
     *
     * @param context
     */
    public static void hideInputMethod(EditText editText, Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 打开输入法
     *
     * @param context
     */
    public static void openInputMethod(EditText editText, Context context) {
//        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.showSoftInput(editText, 0);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);

    }

    /**
     * 切换输入法
     *
     * @param context
     */
    public static void toggleSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static boolean isShowing(Context context){
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return inputMethodManager.isActive();
    }
}
