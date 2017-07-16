package com.emporium.mall.util.attributes;

public enum Grocery {
	ID("id"),
	NAME("name"),
	TYPE("type"),
	QUANTITY("quantity"),
	COUNT("count"),
	PRICE("price");
	
	private String val;
	
	Grocery(String val) {
		this.val = val;
	}
	
	public String val() {
		return this.val;
	}
}