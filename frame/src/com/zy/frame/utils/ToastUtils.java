package com.zy.frame.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/1/8.
 */
public class ToastUtils {
    private static ToastUtils mToastUtils = new ToastUtils();
    private Toast toast;

    public ToastUtils() {
    }

    public static ToastUtils newInstance() {
        return mToastUtils;
    }

    /**
     * display message
     *
     * @param message
     */
    public void displayToast(Context context, String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * cancel toast when toast is showing
     */
    public void cancelToast() {
        if (toast != null)
            toast.cancel();
    }
}
