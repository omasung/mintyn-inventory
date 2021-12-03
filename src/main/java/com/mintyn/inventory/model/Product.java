package com.mintyn.inventory.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "product")

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

    @GenericGenerator(
      name = "product-sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "product_sequence"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
        }
    )

	@Id
    @GeneratedValue(generator = "product-sequence-generator")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;      
    
    @Column(name = "name")
    private String name;    
    
    @Column(name = "stockCount")
    private Integer stockCount;
    
    @Column(name = "price")
    private Double price;
    
    @Column(columnDefinition = "TEXT", name = "description")
    private String description;
    
    @Column(name = "created")
    private LocalDateTime created;
    
    @Column(name = "updated")
    private LocalDateTime updated;
    
	public Product() {
		
    }

    public Product(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

