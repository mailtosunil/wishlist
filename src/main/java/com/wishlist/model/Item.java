package com.wishlist.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bsunil
 *
 */
@Entity
@Table(name = "WISHLIST_ITEMS")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="WISHLIST_ITEM_SEQ", strategy=GenerationType.SEQUENCE)
	private Integer itemId;
	@Column
	private String itemName;
	@Column
	private Double itemValue;
	@Column
	private String itemImgUrl;
	@Column
	private String itemDesc;

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
