package com.mintyn.inventory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mintyn.inventory.dao.ProductDAO;
import com.mintyn.inventory.model.Product;
import com.mintyn.inventory.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired private ProductDAO productDAO;

	@Override
	public void addProduct(Product product) {

		productDAO.addProduct(product);
	}

	@Override
	public void addOrUpdateProduct(Product product) {

		productDAO.addOrUpdateProduct(product);
	}

	@Override
	public void updateProduct(Product product) {

		productDAO.updateProduct(product);
	}

	@Override
	public Product getProductByPk(Long productId) {

		return productDAO.getProductByPk(productId);
	}

	@Override
	public List<Product> getAllProduct() {

		return productDAO.getAllProduct();
	}

	@Override
	public Long getProductTotal() {

		return productDAO.getProductTotal();
	}
	
	@Override
	public List<Product> getAllProductPaginated(int pageSize, int pageNumber) {

		return productDAO.getAllProductPaginated(pageSize, pageNumber);
	}
	
	@Override
	public void deleteProduct(Long productId) {

		productDAO.deleteProduct(productId);
	}

}
