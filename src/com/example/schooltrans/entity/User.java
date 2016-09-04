package com.example.schooltrans.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer uId;

	private String uName;

	private String uPassword;

	private String uEmail;

	private String uTelephone;

	private String uAddress;

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName == null ? null : uName.trim();
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword == null ? null : uPassword.trim();
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail == null ? null : uEmail.trim();
	}

	public String getuTelephone() {
		return uTelephone;
	}

	public void setuTelephone(String uTelephone) {
		this.uTelephone = uTelephone == null ? null : uTelephone.trim();
	}

	public String getuAddress() {
		return uAddress;
	}

	public void setuAddress(String uAddress) {
		this.uAddress = uAddress == null ? null : uAddress.trim();
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uPassword=" + uPassword + ", uEmail=" + uEmail
				+ ", uTelephone=" + uTelephone + ", uAddress=" + uAddress + "]";
	}

}