package com.example.schooltrans.base.impl;

import java.util.ArrayList;

import com.example.schooltrans.R;
import com.example.schooltrans.base.BaseFragment;
import com.example.schooltrans.base.BasePager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 主页面Fragment
 * 
 */
public class ContentFragment extends BaseFragment {

	@ViewInject(R.id.vp_content)
	private ViewPager mViewPager;
	@ViewInject(R.id.rg_group)
	private RadioGroup rgGroup;

	private ArrayList<BasePager> mPagers;

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		ViewUtils.inject(this, view); // 注入view和事件
		// mViewPager = (ViewPager) view.findViewById(R.id.vp_content);
		return view;
	}

	@Override
	public void initData() {
		// 初始化5个标签页面
		mPagers = new ArrayList<BasePager>();
		mPagers.add(new PersonalPager(mActivity));
		mPagers.add(new MessagePager(mActivity));
		mPagers.add(new HomePager(mActivity));
		mPagers.add(new ManagePager(mActivity));
		mPagers.add(new SettingPager(mActivity));
		mViewPager.setAdapter(new ContentAdapter());

		// 低栏标签切换监听
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				switch (arg1) {
				case R.id.rb_personal:
					jumpto(0);
					break;
				case R.id.rb_message:
					jumpto(1);
					break;
				case R.id.rb_home:
					jumpto(2);
					break;
				case R.id.rb_manage:
					jumpto(3);
					break;
				case R.id.rb_setting:
					jumpto(4);
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * 跳转到哪个页面
	 */
	public void jumpto(int pageId) {
		mViewPager.setCurrentItem(pageId, false);
		mPagers.get(pageId).initData();// 切到当前页面,再初始化数据
	}

	class ContentAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pager = mPagers.get(position);
			container.addView(pager.mRootView);// 将页面布局添加到容器中
			// pager.initData();// 初始化数据, 此处尽量不要初始化, 只有切到当前页面,才初始化数据, 节省流量和性能
			return pager.mRootView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

}
