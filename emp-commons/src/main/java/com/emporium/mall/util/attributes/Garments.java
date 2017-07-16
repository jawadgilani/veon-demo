package com.emporium.mall.util.attributes;

public enum Garments {
	TAG("Tag"),
	ID("id"),
	SIZE("size"),
	TYPE("type"),
	QUANTITY("quantity"),
	COUNT("count"),
	PRICE("price"),
	CODE("code");
	
	private String val;
	
	Garments(String val) {
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
