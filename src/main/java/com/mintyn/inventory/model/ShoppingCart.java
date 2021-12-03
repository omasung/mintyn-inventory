package com.mintyn.inventory.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "shoppingcart")

public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;

    @GenericGenerator(
      name = "shoppingcart-sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "shoppingcart_sequence"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
        }
    )

	@Id
    @GeneratedValue(generator = "shoppingcart-sequence-generator")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerOrder")
    private CustomerOrder customerOrder;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;
    
    @Column(name = "price")
    private Double price;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "created")
    private LocalDateTime created;
    
	public ShoppingCart() {
		
    }

    public ShoppingCart(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	@ManyToOne
    @JoinColumn(name = "customerOrder", referencedColumnName = "id")
	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	@ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

}
