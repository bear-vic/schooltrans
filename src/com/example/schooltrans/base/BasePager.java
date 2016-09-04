package com.example.schooltrans.base;

import com.example.schooltrans.R;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

/**
 * 五个标签页的基类
 */
public abstract class BasePager {

	public Activity mActivity;

	private Button login;

	private Button logout;
	
	private SearchView search;

	private Button publish;
	// 标签页面的根布局
	public View mRootView;
	
	public TextView tvTitle;
	// 帧布局容器, 将来要动态向里面添加内容
	public FrameLayout flContent;

	public BasePager(Activity activity) {
		mActivity = activity;
		initView();
	}

	/**
	 * 初始化布局
	 */
	public void initView() {
		mRootView = View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
		flContent = (FrameLayout) mRootView.findViewById(R.id.fl_content);
		login = (Button) mRootView.findViewById(R.id.btn_menu);
		logout = (Button) mRootView.findViewById(R.id.btn_logout);
		publish = (Button) mRootView.findViewById(R.id.bar_publish);
		search=(SearchView) mRootView.findViewById(R.id.bar_search);
	}

	/**
	 * 初始化数据
	 */
	public abstract void initData();

	public Activity getmActivity() {
		return mActivity;
	}

	public View getmRootView() {
		return mRootView;
	}

	public TextView getTvTitle() {
		return tvTitle;
	}

	public FrameLayout getFlContent() {
		return flContent;
	}

	public Button getLogin() {
		return login;
	}

	public Button getLogout() {
		return logout;
	}

	public Button getPublish() {
		return publish;
	}

	public SearchView getSearch() {
		return search;
	}

}
