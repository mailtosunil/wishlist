package com.wishlist.vo;

import java.io.Serializable;

public class ItemForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer itemId;
	private String itemName;
	private Double itemValue;
	private String itemImgUrl;
	private String itemDesc;

	public ItemForm(Integer itemId, String itemName, Double itemValue, String itemImgUrl, String itemDesc) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemValue = itemValue;
		this.itemImgUrl = itemImgUrl;
		this.itemDesc = itemDesc;
	}

	public ItemForm() {
		super();
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	public String getItemImgUrl() {
		return itemImgUrl;
	}

	public void setItemImgUrl(String itemImgUrl) {
		this.itemImgUrl = itemImgUrl;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String toString() {
		return "[" + this.getItemName() + "" + this.getItemDesc() + "" + this.getItemValue() + "]";
	}
}
