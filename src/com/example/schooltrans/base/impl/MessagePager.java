package com.example.schooltrans.base.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.schooltrans.R;
import com.example.schooltrans.base.BasePager;
import com.example.schooltrans.global.MyConstants;
import com.lidroid.xutils.BitmapUtils;
import com.viewpagerindicator.CirclePageIndicator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MessagePager extends BasePager {
	Handler mHandler;

	ViewPager mViewPager;
	CirclePageIndicator indicator;
	List<String> imgList;
	View child;
	long timeDelay=3000;
	public MessagePager(Activity activity) {
		super(activity);
	}

	@SuppressLint("HandlerLeak")
	@Override
	public void initData() {
		tvTitle.setText("我的消息");
		imgList = new ArrayList<>();
		for (int i=1;i<= MyConstants.PCOUNT;i++) {
			imgList.add(MyConstants.PICFILE+i+".jpg");
		}
		child = View.inflate(mActivity, R.layout.pager_message, null);
		mViewPager = (ViewPager) child.findViewById(R.id.pager);
		indicator = (CirclePageIndicator) child.findViewById(R.id.indicator);
		mViewPager.setAdapter(new PicAdapter());
		indicator.setViewPager(mViewPager);
		if (mHandler == null) {
			mHandler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					int currentItem = mViewPager.getCurrentItem();
					if (currentItem < imgList.size() - 1) {
						currentItem++;
					} else {
						currentItem = 0;
					}
					mViewPager.setCurrentItem(currentItem);
					mHandler.sendEmptyMessageDelayed(0, timeDelay);
				};
			};
		} else {
			mHandler.removeCallbacksAndMessages(null);
		}
		mHandler.sendEmptyMessageDelayed(0, timeDelay);
		mViewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					// 删除所有消息
					mHandler.removeCallbacksAndMessages(null);
					break;
				case MotionEvent.ACTION_CANCEL:// 事件取消(当按下后,然后移动下拉刷新,导致抬起后无法响应ACTION_UP,
												// 但此时会响应ACTION_CANCEL,也需要继续播放轮播条)
				case MotionEvent.ACTION_UP:
					mHandler.sendEmptyMessageDelayed(0, timeDelay);
					// 延时2秒切换广告条
					break;
				default:
					break;
				}
				return false;
			}
		});
		getFlContent().addView(child);

	}

	class PicAdapter extends PagerAdapter {

		BitmapUtils mBitmapUtils;

		public PicAdapter() {
			// 初始化xutils中的加载图片的工具
			mBitmapUtils = new BitmapUtils(mActivity);
			// 设置默认加载图片
			mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
		}

		@Override
		public int getCount() {
			return imgList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = new ImageView(mActivity);
			view.setScaleType(ScaleType.FIT_XY);// 设置图片填充效果, 表示填充父窗体
			// 获取图片链接, 使用链接下载图片, 将图片设置给ImageView, 考虑内存溢出问题, 图片本地缓存
			mBitmapUtils.display(view, imgList.get(position));
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}
}