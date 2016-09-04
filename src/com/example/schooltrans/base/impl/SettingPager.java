package com.example.schooltrans.base.impl;

import com.example.schooltrans.R;
import com.example.schooltrans.base.BasePager;

import android.app.Activity;
import android.view.View;


public class SettingPager extends BasePager {
View child;
	public SettingPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		getTvTitle().setText("设置");
		child=View.inflate(mActivity, R.layout.pager_setting, null);
		getFlContent().addView(child);
	}

}
