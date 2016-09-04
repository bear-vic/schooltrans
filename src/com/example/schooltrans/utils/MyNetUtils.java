package com.example.schooltrans.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MyNetUtils {
	String result;

	public String getData(String url) {
		HttpUtils hu = new HttpUtils();
		hu.send(HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				result = arg1;
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				result = arg0.result;
			}
		});
		return result;
	}

	public String getResult() {
		return result;
	}

}
