package com.mintyn.inventory.dao.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mintyn.inventory.dao.CustomerOrderDAO;
import com.mintyn.inventory.model.CustomerOrder;

@Repository
public class CustomerOrderDAOImpl implements CustomerOrderDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(EntityManagerFactory entityManagerFactory) {
		sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
	}

	@Override
	public void addOrder(CustomerOrder customerOrder) {
		sessionFactory.getCurrentSession().save(customerOrder);
	}

	@Override
	public void addOrUpdateOrder(CustomerOrder customerOrder) {
		sessionFactory.getCurrentSession().saveOrUpdate(customerOrder);
	}

	@Override
	public void updateOrder(CustomerOrder customerOrder) {
		sessionFactory.getCurrentSession().update(customerOrder);
	}

	@Override
	public CustomerOrder getOrderByPk(Long customerOrderId) {
		return (CustomerOrder) sessionFactory.getCurrentSession().get(CustomerOrder.class, customerOrderId);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CustomerOrder> getAllOrder() {
		return sessionFactory.getCurrentSession().createQuery("FROM CustomerOrder").list();
	}
	
	@Override
	public Long getTotalOrder() {
		return sessionFactory.getCurrentSession().createQuery("FROM CustomerOrder")
												 .list().stream().count();
	}
	
	@Override
	public Double getTotalOrderAmount() {
		return (Double) sessionFactory.getCurrentSession().createQuery("SELECT sum(amount) FROM CustomerOrder").getSingleResult();
	}
	
	@Override
	public Double getTotalOrderAmountByDate(LocalDate startDate, LocalDate endDate) {
		return (Double) sessionFactory.getCurrentSession().createQuery("SELECT sum(amount) FROM CustomerOrder C WHERE "
																		+ "C.created BETWEEN :stDate AND :edDate")
				
				.setParameter("stDate", startDate)
				.setParameter("edDate", endDate)
				.getSingleResult();
	}
	
	@Override
	public void deleteOrder(Long customerOrderId) {
		CustomerOrder customerOrder = (CustomerOrder) sessionFactory.getCurrentSession().load(CustomerOrder.class, customerOrderId);
		if (customerOrder != null) {
			this.sessionFactory.getCurrentSession().delete(customerOrder);
		}
	}
	
}
