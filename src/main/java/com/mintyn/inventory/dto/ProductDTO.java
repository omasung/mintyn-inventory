package com.mintyn.inventory.dto;

import java.time.LocalDateTime;

public class ProductDTO {

	private Long productId;				//Product Id      
	private String name;				//Product Name    
	private Integer stockCount;			// Available Number Of Product
	private Double price;				//Product Price
	private String description;			//Product Description
	private LocalDateTime created;		//When The Product Was Created
	private LocalDateTime updated;		//When The Product Was Updated
	
	public ProductDTO(Long productId, String name, Integer stockCount, Double price, String description,
			LocalDateTime created, LocalDateTime updated) {
		
		super();
		
		this.productId = productId;
		this.name = name;
		this.stockCount = stockCount;
		this.price = price;
		this.description = description;
		this.created = created;
		this.updated = updated;
		
	}

	public ProductDTO() {
		
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
	
}
