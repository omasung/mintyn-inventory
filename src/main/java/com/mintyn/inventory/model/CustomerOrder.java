package com.mintyn.inventory.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "customerorder")

public class CustomerOrder implements Serializable {

	private static final long serialVersionUID = 1L;

    @GenericGenerator(
      name = "customerorder-sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "customerorder_sequence"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
        }
    )

	@Id
    @GeneratedValue(generator = "customerorder-sequence-generator")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "amount")
    private Double amount;
    
    @Column(name = "created")
    private LocalDate created;
    
	public CustomerOrder() {
		
    }

    public CustomerOrder(Long id) {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

}
