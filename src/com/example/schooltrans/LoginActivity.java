package com.example.schooltrans;

import com.example.schooltrans.entity.User;
import com.example.schooltrans.global.MyConstants;
import com.example.schooltrans.utils.MyLog;
import com.example.schooltrans.utils.PrefUtils;
import com.example.schooltrans.utils.ToastUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * 主页面
 * 
 */
public class LoginActivity extends FragmentActivity {
	@ViewInject(R.id.landed_account)
	private EditText etUname;
	@ViewInject(R.id.landed_password)
	private EditText etUPassword;
	@ViewInject(R.id.login_che)
	private CheckBox remember;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉标题, 必须在setContentView之前执行
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		ViewUtils.inject(this);
		init();
	}

	public void init() {
		etUname.setText(PrefUtils.getString("uName", "", LoginActivity.this));
		etUPassword.setText(PrefUtils.getString("uPassword", "", LoginActivity.this));
		remember.setChecked(PrefUtils.getBoolean("checked", false, LoginActivity.this));
	}

	@OnClick(R.id.login)
	public void login(View v) {
		String uName = etUname.getText().toString().trim();
		String uPassword = etUPassword.getText().toString().trim();
		if (remember.isChecked()) {
			PrefUtils.putString("uName", uName, LoginActivity.this);
			PrefUtils.putString("uPassword", uPassword, LoginActivity.this);
			PrefUtils.putBoolean("checked", true, LoginActivity.this);
		} else {
			PrefUtils.remove("uName", LoginActivity.this);
			PrefUtils.remove("uPassword", LoginActivity.this);
			PrefUtils.remove("checked", LoginActivity.this);
		}
		RequestParams params = new RequestParams();
		params.addBodyParameter("uName", uName);
		params.addBodyParameter("uPassword", uPassword);
		getDataFromServer(params);
	}

	/**
	 * 从服务器获取数据 需要权限:
	 * <uses-permission android:name="android.permission.INTERNET"/>
	 */
	private void getDataFromServer(RequestParams prams) {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.POST, MyConstants.LOGIN, prams, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// 请求成功
				String result = responseInfo.result;
				// MyLog.v(result);

				if (result != null && !result.trim().equalsIgnoreCase("")) {
					Gson gson = new Gson();
					User user = gson.fromJson(result, User.class);
					Intent intent = new Intent();
					intent.putExtra("user", user);
					setResult(MyConstants.REQID, intent);
					finish();
				} else {
					ToastUtils.showToast(LoginActivity.this, "账号或密码错误");
				}

			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// 请求失败
				MyLog.v(msg);
				ToastUtils.showToast(LoginActivity.this, "服务器忙...");
			}
		});
	}

	@Override
	public void onBackPressed() {
		setResult(MyConstants.REQID, null);
		finish();
	}
}
