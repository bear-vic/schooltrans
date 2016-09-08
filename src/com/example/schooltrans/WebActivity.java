package com.example.schooltrans;

import com.example.schooltrans.view.ProgressWebView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 主页面
 * 
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebActivity extends Activity {
	@ViewInject(R.id.webview)
	ProgressWebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		ViewUtils.inject(this);
		System.out.println(savedInstanceState + "");
		Bundle b = getIntent().getExtras();
		String url = b.getString("url");
		init(url);
	}

	private void init(String url) {
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
