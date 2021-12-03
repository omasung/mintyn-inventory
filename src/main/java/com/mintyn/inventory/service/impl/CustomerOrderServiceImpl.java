package com.mintyn.inventory.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mintyn.inventory.dao.CustomerOrderDAO;
import com.mintyn.inventory.model.CustomerOrder;
import com.mintyn.inventory.service.CustomerOrderService;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {
	
	@Autowired private CustomerOrderDAO customerOrderDAO;

	@Override
	public void addOrder(CustomerOrder customerOrder) {

		customerOrderDAO.addOrder(customerOrder);
	}

	@Override
	public void addOrUpdateOrder(CustomerOrder customerOrder) {

		customerOrderDAO.addOrUpdateOrder(customerOrder);
	}

	@Override
	public void updateOrder(CustomerOrder customerOrder) {

		customerOrderDAO.updateOrder(customerOrder);
	}

	@Override
	public CustomerOrder getOrderByPk(Long customerOrderId) {

		return customerOrderDAO.getOrderByPk(customerOrderId);
	}

	@Override
	public List<CustomerOrder> getAllOrder() {

		return customerOrderDAO.getAllOrder();
	}

	@Override
	public Long getTotalOrder() {

		return customerOrderDAO.getTotalOrder();
	}

	@Override
	public Double getTotalOrderAmount() {

		return customerOrderDAO.getTotalOrderAmount();
	}

	@Override
	public Double getTotalOrderAmountByDate(LocalDate startDate, LocalDate endDate) {

		return customerOrderDAO.getTotalOrderAmountByDate(startDate, endDate);
	}
	
	@Override
	public void deleteOrder(Long customerOrderId) {

		customerOrderDAO.deleteOrder(customerOrderId);
	}

}
