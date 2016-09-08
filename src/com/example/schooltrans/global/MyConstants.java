package com.example.schooltrans.global;

public class MyConstants {
	public static final String PRO = "/schoolbargin";
	// 服务器地址
	public static final String SERVER_URL = "http://192.168.98.105:8080/";
	// 用户登录url
	public static final String LOGIN = SERVER_URL + PRO + "/user/clogin";
	// 用户登录状态
	public static final String LOGINSTATUS = "login_status";
	// activity返回码
	public static final int REQID = 1;
	// bundle中的对象key值
	public static final String BUNDLE_USER = "user";
	// 查看服务器所有物品url
	public static final String LOOKALL = SERVER_URL + PRO + "/goods/clookall";
	// 查看自己物品url
	public static final String LOOKMINE = SERVER_URL + PRO + "/goods/clookmine";
	// 全局管理userManager的tag
	public static final String UMTAG = "user";
	// 服务器为手机存放资源url
	public static final String PICFILE = SERVER_URL + "/pic/forphone/";
	// 资源数量
	public static final int PCOUNT = 7;
	public static final String LOOKDETAIL = SERVER_URL + PRO + "/comments/watch";
	public static final String URL = "url";
}
