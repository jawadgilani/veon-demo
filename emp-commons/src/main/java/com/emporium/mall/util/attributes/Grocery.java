package com.emporium.mall.util.attributes;

public enum Grocery {
	ID("id"),
	NAME("name"),
	TYPE("type"),
	QUANTITY("quantity"),
	COUNT("count"),
	PRICE("price"),
	CODE("code");
	
	
	private String val;
	
	Grocery(String val) {
		this.val = val;
	}
	
	public String val() {
		return this.val;
	}
	
	@Override
	public String toString() {
		return this.val;
	}
}