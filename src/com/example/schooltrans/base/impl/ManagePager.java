package com.example.schooltrans.base.impl;

import java.util.ArrayList;

import com.example.schooltrans.R;
import com.example.schooltrans.base.BasePager;
import com.example.schooltrans.entity.User;
import com.example.schooltrans.entity.ex.ExGoods;
import com.example.schooltrans.global.MyConstants;
import com.example.schooltrans.service.UserManager;
import com.example.schooltrans.utils.MyBitmapUtils;
import com.example.schooltrans.utils.MyJsonUtils;
import com.example.schooltrans.utils.MyLog;
import com.example.schooltrans.utils.ToastUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ManagePager extends BasePager {
	ViewHolder holder;
	ArrayList<ExGoods> bList;
	View child;
	GridView gv;
	MyBitmapUtils pu = new MyBitmapUtils();

	public ManagePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tvTitle.setText("我的商品");
		Button btn = getPublish();
		btn.setVisibility(View.VISIBLE);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ToastUtils.showToast(mActivity, "功能完善中");
			}
		});
		child = View.inflate(mActivity, R.layout.pager_manage, null);
		UserManager um = UserManager.getInstance();
		if (!um.shouldLogin()) {
			RequestParams params = new RequestParams();
			User user = um.findUser(MyConstants.UMTAG);
			params.addBodyParameter("uId", String.valueOf(user.getuId()));
			getDataFromServer(params);
		} else {
			ToastUtils.showToast(getmActivity(), "请先登录");
		}
	}

	/**
	 * 从服务器获取数据 需要权限:
	 * <uses-permission android:name="android.permission.INTERNET"/>
	 */
	private void getDataFromServer(RequestParams prams) {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.POST, MyConstants.LOOKMINE, prams, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// 请求成功
				String result = responseInfo.result;// 获取json字符串
				bList = handleResult(result);
				MyLog.i("start view");
				gv = (GridView) child.findViewById(R.id.gv_list);
				MyLog.i("bList" + bList.size());
				MyAdapter m = new MyAdapter(DisplayMode.GRIDVIEW);
				gv.setAdapter(m);
				getFlContent().removeAllViews();
				getFlContent().addView(child);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// 请求失败
				error.printStackTrace();
				MyLog.i(msg);
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@SuppressWarnings("unchecked")
	private ArrayList<ExGoods> handleResult(String result) {
		MyLog.i("result", result);
		result = MyJsonUtils.handleMyGoods(result);
		MyLog.i("managepager:", result);
		ArrayList<Object> arr = new ArrayList<Object>();
		Gson gson = new Gson();
		arr = gson.fromJson(result, ArrayList.class);
		ArrayList<ExGoods> rsList = new ArrayList<ExGoods>();
		for (Object object : arr) {
			String temp = object.toString();
			MyLog.i(temp);
			ExGoods b = gson.fromJson(temp, ExGoods.class);
			MyLog.i(b + "");
			rsList.add(b);
		}
		return rsList;
	}

	static class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView price;
		public TextView desc;
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

		@Override
		public int getCount() {
			return bList.size();
		}

		@Override
		public ExGoods getItem(int position) {
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
				holder.desc = (TextView) convertView.findViewById(R.id.tv_desc);
				holder.price = (TextView) convertView.findViewById(R.id.tv_price);
				convertView.setTag(holder);
				return convertView;
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			ExGoods item = getItem(position);
			String url = MyConstants.SERVER_URL + item.getgPic().replaceAll("%", "/");
			pu.display(holder.img, url);
			holder.title.setText("标题:\t" + item.getgTitle());
			holder.price.setText("价格:\t" + item.getgPrice() + "元");
			holder.date.setText("上架时间:\t" + item.getExDate());
			holder.desc.setText("具体描述:\t" + item.getgDescrption());
			return convertView;
		}
	}
}
