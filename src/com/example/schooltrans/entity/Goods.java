package com.example.schooltrans.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Goods implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer gId;

	private String gTitle;

	private Integer gUid;

	private BigDecimal gPrice;

	private String gName;

	private String gPic;

	private Date gDate;

	private String gDescrption;

	public Integer getgId() {
		return gId;
	}

	public void setgId(Integer gId) {
		this.gId = gId;
	}

	public String getgTitle() {
		return gTitle;
	}

	public void setgTitle(String gTitle) {
		this.gTitle = gTitle == null ? null : gTitle.trim();
	}

	public Integer getgUid() {
		return gUid;
	}

	public void setgUid(Integer gUid) {
		this.gUid = gUid;
	}

	public BigDecimal getgPrice() {
		return gPrice;
	}

	public void setgPrice(BigDecimal gPrice) {
		this.gPrice = gPrice;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName == null ? null : gName.trim();
	}

	public String getgPic() {
		return gPic;
	}

	public void setgPic(String gPic) {
		this.gPic = gPic == null ? null : gPic.trim();
	}

	public Date getgDate() {
		return gDate;
	}

	public void setgDate(Date gDate) {
		this.gDate = gDate;
	}

	public String getgDescrption() {
		return gDescrption;
	}

	public void setgDescrption(String gDescrption) {
		this.gDescrption = gDescrption == null ? null : gDescrption.trim();
	}

	@Override
	public String toString() {
		return "Goods [gId=" + gId + ", gTitle=" + gTitle + ", gUid=" + gUid + ", gPrice=" + gPrice + ", gName=" + gName
				+ ", gPic=" + gPic + ", gDate=" + gDate + ", gDescrption=" + gDescrption + "]";
	}
	
}