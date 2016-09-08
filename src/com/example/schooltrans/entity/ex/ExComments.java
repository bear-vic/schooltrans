/**
 * @description 留言扩展类
 * @date 2016-8-26
 * @author hxg	
 */
package com.example.schooltrans.entity.ex;

import java.io.Serializable;

import com.example.schooltrans.entity.Comments;


public class ExComments extends Comments implements Serializable{
	private static final long serialVersionUID = 6083158608860993785L;
	private String exDate; //格式化日期
	private String exUname; //用户名称

	public String getExUname() {
		return exUname;
	}

	public void setExUname(String exUname) {
		this.exUname = exUname;
	}

	public String getExDate() {
		return exDate;
	}

	public void setExDate(String exDate) {
		this.exDate = exDate;
	}

}
