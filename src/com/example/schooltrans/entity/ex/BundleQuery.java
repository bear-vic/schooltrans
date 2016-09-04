/*
 * **
 * @description 查询结果，含多个对象
 * @date 2016-8-26
 * @author hxg	
 */
package com.example.schooltrans.entity.ex;

import java.io.Serializable;
import java.util.List;

import com.example.schooltrans.entity.User;

public class BundleQuery implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	private ExGoods goods; // 物品
	private User user; // 用户
	private List<ExComments> commentsList; // 所有评论

	public List<ExComments> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<ExComments> commentsList) {
		this.commentsList = commentsList;
	}

	public ExGoods getGoods() {
		return goods;
	}

	public void setGoods(ExGoods goods) {
		this.goods = (ExGoods) goods;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "BundleQuery [goods=" + goods + ", user=" + user + ", commentsList=" + commentsList + "]";
	}
	
}
