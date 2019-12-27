package com.laptopmall.bean;

import java.math.BigDecimal;

public class OrderItem {
	private Integer id;
	private Integer user_id;
	private Integer orderId;
	private String productName;
	private String productImage;
	private BigDecimal productPrice;
	private Integer quantity;
	private BigDecimal totalPrice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", productName=" + productName + ", productImage="
				+ productImage + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
	}
	
}
