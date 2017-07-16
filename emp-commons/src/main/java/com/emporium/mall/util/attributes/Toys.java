package com.emporium.mall.util.attributes;

public enum Toys {
	ID("id"),
	AGE("age"),
	TYPE("type"),
	QUANTITY("quantity"),
	COUNT("count"),
	PRICE("price");
	
	private String val;
	
	Toys(String val) {
		this.val = val;
	}
	
	public String val() {
		return this.val;
	}
}
