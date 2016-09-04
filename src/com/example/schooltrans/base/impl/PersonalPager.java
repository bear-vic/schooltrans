package com.example.schooltrans.base.impl;

import com.example.schooltrans.LoginActivity;
import com.example.schooltrans.MainActivity;
import com.example.schooltrans.R;
import com.example.schooltrans.base.BasePager;
import com.example.schooltrans.entity.User;
import com.example.schooltrans.global.MyConstants;
import com.example.schooltrans.service.UserManager;
import com.example.schooltrans.utils.MyViewUtils;
import com.example.schooltrans.utils.ToastUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PersonalPager extends BasePager {
	View child;
	private Bundle bundle;
	private int[] res = { R.id.tv_uname, R.id.tv_email, R.id.tv_address, R.id.tv_tel };
	Button login;
	Button logout;
	boolean flag;

	public PersonalPager(Activity activity, Bundle bundle) {
		super(activity);
		this.setBundle(bundle);
	}

	public PersonalPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		login = getLogin();
		logout = getLogout();
		getTvTitle().setText("个人中心");
		MyBtnListener btnListener = new MyBtnListener();
		login.setOnClickListener(btnListener);
		logout.setOnClickListener(btnListener);
		child = View.inflate(mActivity, R.layout.pager_user, null);
		UserManager um = UserManager.getInstance();
		if (um.shouldLogin()) {
			login.setVisibility(View.VISIBLE);
			ToastUtils.showToast(getmActivity(), "你还没登录哦！亲");
		} else {
			logout.setVisibility(View.VISIBLE);
			User u = um.findUser("user");
			String[] contentValue = { u.getuName(), u.getuEmail(), u.getuTelephone(), u.getuTelephone() };
			MyViewUtils.initAllEt(child, res, contentValue);
		}
		getFlContent().addView(child);
	}

	public Bundle getBundle() {
		return bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	class MyBtnListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_menu:
				Intent intent = new Intent(getmActivity(), LoginActivity.class);
				getmActivity().startActivityForResult(intent, MyConstants.REQID);
				break;
			case R.id.btn_logout:
				UserManager.getInstance().exitUser();
				logout.setVisibility(View.GONE);
				login.setVisibility(View.VISIBLE);
				((MainActivity) getmActivity()).initFragment(new ContentFragment());
			default:
				break;
			}
		}
	}
}
