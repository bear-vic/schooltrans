package com.example.schooltrans.base.impl;

import java.util.ArrayList;

import com.example.schooltrans.MainActivity;
import com.example.schooltrans.R;
import com.example.schooltrans.base.BasePager;
import com.example.schooltrans.entity.ex.BundleQuery;
import com.example.schooltrans.global.MyConstants;
import com.example.schooltrans.utils.MyJsonUtils;
import com.example.schooltrans.utils.MyLog;
import com.example.schooltrans.utils.ToastUtils;
import com.example.schooltrans.view.RefreshListView;
import com.example.schooltrans.view.RefreshListView.OnRefreshListener;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePager extends BasePager {
	RefreshListView rf;
	ViewHolder holder;
	ArrayList<BundleQuery> bList;
	View child;
	BitmapUtils pu = new BitmapUtils(mActivity);
	private MyAdapter mAdapter;

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tvTitle.setText("主页");
		getSearch().setVisibility(View.VISIBLE);
		child = View.inflate(getmActivity(), R.layout.pager_home, null);
		rf = (RefreshListView) child.findViewById(R.id.pag_list);
		getDataFromServer(null);
		rf.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
				BundleQuery b = mAdapter.getItem(position);
				String url = MyConstants.LOOKDETAIL + "?goodsId=" + b.getGoods().getgId();
				MainActivity m = (MainActivity) getmActivity();
				WebFragment fragment = new WebFragment();
				Bundle bundle = new Bundle();
				bundle.putString(MyConstants.URL, url);
				fragment.setArguments(bundle);
				m.replaceFragment(fragment);
			}
		});
		rf.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				getDataFromServer(null);
			}

			@Override
			public void loadMore() {
				rf.onRefreshComplete(true);
			}
		});
	}

	/**
	 * 从服务器获取数据 需要权限:
	 * <uses-permission android:name="android.permission.INTERNET"/>
	 */
	private void getDataFromServer(RequestParams prams) {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.POST, MyConstants.LOOKALL, prams, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// 请求成功
				String result = responseInfo.result;// 获取json字符串
				try {
					bList = handleResult(result);
					mAdapter = new MyAdapter();
					rf.setAdapter(mAdapter);
					getFlContent().removeAllViews();
					getFlContent().addView(child);
					rf.onRefreshComplete(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// 请求失败
				error.printStackTrace();
				ToastUtils.showToast(getmActivity(), "服务器忙...");
			}
		});
	}

	/**
	 * 处理服务器json数据
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<BundleQuery> handleResult(String result) throws Exception {
		MyLog.i(result);
		result = MyJsonUtils.handleJsonResult(result);
		MyLog.i(result);
		ArrayList<Object> arr = new ArrayList<Object>();
		Gson gson = new Gson();
		arr = gson.fromJson(result, ArrayList.class);
		ArrayList<BundleQuery> rsList = new ArrayList<BundleQuery>();
		for (Object object : arr) {
			String temp = object.toString();
			MyLog.i(temp);
			// System.out.println(temp.substring(157,170));
			BundleQuery b = gson.fromJson(temp, BundleQuery.class);
			MyLog.i(b + "");
			rsList.add(b);
		}
		return rsList;
	}

	static class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView price;
		public TextView uname;
		public TextView date;
	}

	public enum DisplayMode {
		GRIDVIEW, LISTVIEW,
	}

	public class MyAdapter extends BaseAdapter {
		DisplayMode mode;

		public MyAdapter(DisplayMode mode) {
			this.mode = mode;
		}

		public MyAdapter() {
		}

		@Override
		public int getCount() {
			return bList.size();
		}

		@Override
		public BundleQuery getItem(int position) {
			return bList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				holder = new ViewHolder();
				if (mode != null) {
					if (mode == DisplayMode.GRIDVIEW) {
						convertView = View.inflate(getmActivity(), R.layout.grid_item_goods, null);
					}
				} else {
					convertView = View.inflate(getmActivity(), R.layout.list_item_goods, null);
				}
				holder.img = (ImageView) convertView.findViewById(R.id.iv_icon);
				holder.title = (TextView) convertView.findViewById(R.id.tv_title);
				holder.date = (TextView) convertView.findViewById(R.id.tv_date);
				holder.uname = (TextView) convertView.findViewById(R.id.tv_uname);
				holder.price = (TextView) convertView.findViewById(R.id.tv_price);
				convertView.setTag(holder);
				return convertView;
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			BundleQuery item = getItem(position);
			String url = MyConstants.SERVER_URL + item.getGoods().getgPic().replaceAll("%", "/");
			pu.display(holder.img, url);
			holder.title.setText("标题:\t" + item.getGoods().getgTitle());
			holder.price.setText("价格:\t" + item.getGoods().getgPrice() + "元");
			holder.uname.setText("卖家:\t" + item.getUser().getuName());
			holder.date.setText("上架时间:\t" + item.getGoods().getExDate());
			return convertView;
		}
	}
}
