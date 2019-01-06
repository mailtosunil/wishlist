package com.wishlist.vo;

public class Product {

	/**
	 * 
	 */
	private Integer id;
	private String name;
	private String type;
	private String desc;
	private Double value;

	public Product() {
		super();
	}

	public Product(Integer id, String name, String type, String desc, Double value) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.desc = desc;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
