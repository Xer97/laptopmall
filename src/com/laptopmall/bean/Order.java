package com.laptopmall.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
	private Integer id;
	private Integer userId;
	private BigDecimal payment;
	private Date payTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", payment=" + payment + ", payTime=" + payTime + "]";
	}
	
}
