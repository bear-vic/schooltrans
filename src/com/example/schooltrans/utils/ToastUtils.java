package com.example.schooltrans.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;


/**
 * ToastUtils
 *
 */
public class ToastUtils {
    private static Toast toast = null; //Toast的对象！
    private static Snackbar snack;

    public static void showToast(Context mContext, String id) {
        if (toast == null) {
            toast = Toast.makeText(mContext, id, Toast.LENGTH_SHORT);
        } else {
            toast.setText(id);
        }
        toast.show();
    }

    public static Snackbar showSnackbar(Context mContext, View v, String id) {
        if (snack == null) {
            snack = Snackbar.make(v, id, Snackbar.LENGTH_SHORT);
        } else {
            snack.setText(id);
        }
        snack.show();
        return snack;
    }

    public static Snackbar showSnackbar(View v, String id) {
        return showSnackbar(ApplicationUtil.getContext(), v, id);
    }
}
