package com.wishlist.model;

import java.io.Serializable;

/**
 * @author bsunil
 *
 */
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer itemId;
	private String itemName;
	private Double itemValue;
	private String itemImgUrl;
	private String itemDesc;

	
	public Item(Integer itemId, String itemName, Double itemValue, String itemImgUrl, String itemDesc) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemValue = itemValue;
		this.itemImgUrl = itemImgUrl;
		this.itemDesc = itemDesc;
	}

	public Item() {
		super();
		// TODO Auto-generated constructor stub
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
}
