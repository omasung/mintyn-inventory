package com.mintyn.inventory.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mintyn.inventory.dao.ProductDAO;
import com.mintyn.inventory.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(EntityManagerFactory entityManagerFactory) {
		sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
	}

	@Override
	public void addProduct(Product product) {
		sessionFactory.getCurrentSession().save(product);
	}

	@Override
	public void addOrUpdateProduct(Product product) {
		sessionFactory.getCurrentSession().saveOrUpdate(product);
	}

	@Override
	public void updateProduct(Product product) {
		sessionFactory.getCurrentSession().update(product);
	}

	@Override
	public Product getProductByPk(Long productId) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, productId);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getAllProduct() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product").list();
	}
	
	@Override
	public Long getProductTotal() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product")
				                                 .list().stream().count();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductPaginated(int pageSize, int pageNumber) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Product ORDER BY id DESC")

				.setFirstResult(pageSize * pageNumber)//page number = pageSize * pageNumber
				.setMaxResults(pageSize)//pageSize
				
				.list();
	}
	
	@Override
	public void deleteProduct(Long productId) {
		Product product = (Product) sessionFactory.getCurrentSession().load(Product.class, productId);
		if (product != null) {
			this.sessionFactory.getCurrentSession().delete(product);
		}
	}
	
}
