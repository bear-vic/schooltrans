/**
 * @description 商品的扩展类
 * @date 2016-8-20
 * @author hxg	
 */
package com.example.schooltrans.entity.ex;

import java.io.Serializable;

import com.example.schooltrans.entity.Goods;

public class ExGoods extends Goods implements Serializable {
	private static final long serialVersionUID = -6074876787179340794L;
	private String exDate; // 格式化日期

	public String getExDate() {
		return exDate;
	}

	public void setExDate(String exDate) {
		this.exDate = exDate;
	}
	

}
