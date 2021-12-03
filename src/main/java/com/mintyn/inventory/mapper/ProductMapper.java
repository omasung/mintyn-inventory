package com.mintyn.inventory.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mintyn.inventory.dto.ProductDTO;
import com.mintyn.inventory.model.Product;
import com.mintyn.inventory.service.ProductService;

@Service
@Transactional
public class ProductMapper {

	@Autowired private ProductService productService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProductDTO> mapperFindAllProductPaginated(int pageSize, int pageNumber) {

		Product pd = null;
		ArrayList productList = new ArrayList();

		List<Product> product;
		product = productService.getAllProductPaginated(pageSize, pageNumber - 1);

		if (!product.isEmpty()) {

			Collection coll = null;

			coll = (Collection) product;

			Iterator i = coll.iterator();

			while (i.hasNext()) {

				pd = (Product) i.next();

				productList.add(new ProductDTO (pd.getId(), pd.getName(), pd.getStockCount(), pd.getPrice(),
															pd.getDescription(), pd.getCreated(), pd.getUpdated()));

			}

		}

		return productList;
	}
	
}
