package com.uplus.eureka.member.model.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Member implements Serializable {
	private static final long serialVersionUID=1L;
	
	@Schema(description = "아이디" 	, example = "eurekauplus")
	private String id;
	@Schema(description = "비밀번호" 	, example = "1111")
	private String password;
	@Schema(description = "이름" 	, example = "유레카")
	private String name;
	@Schema(description = "이메일" 	, example = "eurekauplus@eureka.com")
	private String email;
	@Schema(description = "주소" 	, example = "서울시")
	private String address;
	@Schema(description = "전화번호" 	, example = "010")
	private String phone;
	@Schema(description = "탈퇴 여부" , example = "N")
	private String withdraw;
	public Member() {}
	
	public String getWithdraw() {
		return withdraw;
	}


	public void setWithdraw(String withdraw) {
		this.withdraw = withdraw;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [id=").append(id).append(", password=").append(password).append(", name=").append(name)
				.append(", email=").append(email).append(", address=").append(address).append(", phone=").append(phone)
				.append("]");
		return builder.toString();
	}
}
