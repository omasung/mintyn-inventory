package com.mintyn.inventory.dao;

import java.util.List;

import com.mintyn.inventory.model.Product;

public interface ProductDAO {

	void addProduct(Product product);

	void addOrUpdateProduct(Product product);

	void updateProduct(Product product);

	Product getProductByPk(Long productId);

	List<Product> getAllProduct();
	
	Long getProductTotal();
	
	List<Product> getAllProductPaginated(int pageSize, int pageNumber);

	void deleteProduct(Long productId);

}
