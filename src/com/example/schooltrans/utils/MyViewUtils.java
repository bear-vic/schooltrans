/**
 * 
 * 视图工具类
 * */

package com.example.schooltrans.utils;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewUtils {
	/**
	 * 初始化所有textview并设置内容
	 */
	public static void initAllEt(View v, int[] resId, String[] contentValue) {
		for (int i = 0; i < resId.length; i++) {
			((EditText) (v.findViewById(resId[i]))).setText(contentValue[i]);
		}
	}
	/**
	 * 初始化所有textview并设置内容
	 */
	public static void initAllTv(View v, int[] resId, String[] contentValue) {
		for (int i = 0; i < resId.length; i++) {
			((TextView) (v.findViewById(resId[i]))).setText(contentValue[i]);
		}
	}

	/**
	 * 初始化所有存在的按钮并绑定事件
	 */
	public void initAllBtn(View v, int[] resId, OnClickListener btnListener) {
		for (int i = 0; i < resId.length; i++) {
			((Button) v.findViewById(resId[i])).setOnClickListener(btnListener);
		}
	}

	/**
	 * 初始化所有图片并设置地址
	 */
	public void initAllImv(View v, int[] resId, int[] imgSrc) {
		for (int i = 0; i < resId.length; i++) {
			((ImageView) v.findViewById(resId[i])).setBackgroundResource(imgSrc[i]);
		}
	}
}
