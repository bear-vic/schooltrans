package com.example.schooltrans.base.impl;

import com.example.schooltrans.R;
import com.example.schooltrans.base.BaseFragment;
import com.example.schooltrans.global.MyConstants;
import com.example.schooltrans.view.ProgressWebView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 主页面Fragment
 * 
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebFragment extends BaseFragment {
	@ViewInject(R.id.webview)
	ProgressWebView webView;

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.web, null);
		ViewUtils.inject(this, view); // 注入view和事件
		return view;
	}

	@Override
	public void initData() {
		String url = getArguments().getString(MyConstants.URL);
		WebSettings setting = webView.getSettings();
		setting.setJavaScriptEnabled(true);
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}

}
