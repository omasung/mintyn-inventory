package com.mintyn.inventory.service;

import java.time.LocalDate;
import java.util.List;

import com.mintyn.inventory.model.CustomerOrder;

public interface CustomerOrderService {
	
	void addOrder(CustomerOrder customerOrder);

	void addOrUpdateOrder(CustomerOrder customerOrder);

	void updateOrder(CustomerOrder customerOrder);

	CustomerOrder getOrderByPk(Long customerOrderId);

	List<CustomerOrder> getAllOrder();
	
	Long getTotalOrder();
	
	Double getTotalOrderAmount();
	
	Double getTotalOrderAmountByDate(LocalDate startDate, LocalDate endDate);

	void deleteOrder(Long customerOrderId);

}
