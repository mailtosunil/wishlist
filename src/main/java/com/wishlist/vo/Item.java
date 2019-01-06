package com.wishlist.vo;

import java.io.Serializable;

public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type;
	private Double value;
	private String imgUrl;
	private String desc;

	public Item(Integer id, String type, Double value, String imgUrl, String desc) {
		super();
		this.id = id;
		this.type = type;
		this.value = value;
		this.imgUrl = imgUrl;
		this.desc = desc;
	}

	public Item() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String toString() {
		return "[" + this.getType() + "" + this.getDesc() + "" + this.getValue() + "]";
	}

}
