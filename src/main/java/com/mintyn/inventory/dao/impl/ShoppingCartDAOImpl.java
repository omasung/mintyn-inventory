package com.mintyn.inventory.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mintyn.inventory.dao.ShoppingCartDAO;
import com.mintyn.inventory.model.ShoppingCart;

@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(EntityManagerFactory entityManagerFactory) {
		sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
	}

	@Override
	public void addCart(ShoppingCart shoppingCart) {
		sessionFactory.getCurrentSession().save(shoppingCart);
	}

	@Override
	public void addOrUpdateCart(ShoppingCart shoppingCart) {
		sessionFactory.getCurrentSession().saveOrUpdate(shoppingCart);
	}

	@Override
	public void updateCart(ShoppingCart shoppingCart) {
		sessionFactory.getCurrentSession().update(shoppingCart);
	}

	@Override
	public ShoppingCart getCartByPk(Long shoppingCartId) {
		return (ShoppingCart) sessionFactory.getCurrentSession().get(ShoppingCart.class, shoppingCartId);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ShoppingCart> getAllCart() {
		return sessionFactory.getCurrentSession().createQuery("FROM ShoppingCart").list();
	}
	
	@Override
	public void deleteCart(Long shoppingCartId) {
		ShoppingCart shoppingCart = (ShoppingCart) sessionFactory.getCurrentSession().load(ShoppingCart.class, shoppingCartId);
		if (shoppingCart != null) {
			this.sessionFactory.getCurrentSession().delete(shoppingCart);
		}
	}
	
}
