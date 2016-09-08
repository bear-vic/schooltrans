package com.example.schooltrans;

import com.example.schooltrans.base.impl.ContentFragment;
import com.example.schooltrans.entity.User;
import com.example.schooltrans.global.MyConstants;
import com.example.schooltrans.service.UserManager;
import com.example.schooltrans.utils.ToastUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

/**
 * 主页面
 * 
 */
public class MainActivity extends FragmentActivity {

	private static final String TAG_CONTENT = "TAG_CONTENT";
	Bundle bundle;
	UserManager um;
	long exit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉标题, 必须在setContentView之前执行
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		ContentFragment cf = new ContentFragment();
		initFragment(cf);
	}

	/**
	 * 初始化Fragment
	 */
	public void initFragment(Fragment fragment) {
		// Fragment管理器
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();// 开始事务
		// 将帧布局替换为对应的Fragment
		transaction.replace(R.id.fl_content, fragment, TAG_CONTENT);
		transaction.addToBackStack(TAG_CONTENT);
		transaction.commit();// 提交事务
	}

	public void replaceFragment(Fragment fragment) {
		// Fragment管理器
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();// 开始事务
		transaction.replace(R.id.fl_content, fragment);
		transaction.commit();// 提交事务
	}

	/**
	 * 获取主页对象
	 * 
	 * @return
	 */
	public ContentFragment getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment fragment = (ContentFragment) fm.findFragmentByTag(TAG_CONTENT);
		return fragment;
	}

	@Override
	protected void onResume() {
		super.onResume();
		ContentFragment content = new ContentFragment();
		initFragment(content);
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		if (arg2 != null) {
			um = UserManager.getInstance();
			bundle = arg2.getExtras();
			um.addUser(MyConstants.UMTAG, (User) bundle.get("user"));
		}
	}

	@Override
	public void onBackPressed() {
		initFragment(getContentFragment());
		if (System.currentTimeMillis() - exit >= 2000) {
			//ToastUtils.showToast(MainActivity.this, "再按一次退出");
			exit = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}
	}
}
