package com.example.schooltrans.entity;

import java.io.Serializable;
import java.util.Date;

public class Comments  implements Serializable{
    private static final long serialVersionUID = 1L;

	private Integer cId;

    private Integer cUser;

    private Integer cGoods;

    private Date cDate;

    private String cComments;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getcUser() {
        return cUser;
    }

    public void setcUser(Integer cUser) {
        this.cUser = cUser;
    }

    public Integer getcGoods() {
        return cGoods;
    }

    public void setcGoods(Integer cGoods) {
        this.cGoods = cGoods;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public String getcComments() {
        return cComments;
    }

    public void setcComments(String cComments) {
        this.cComments = cComments == null ? null : cComments.trim();
    }
}