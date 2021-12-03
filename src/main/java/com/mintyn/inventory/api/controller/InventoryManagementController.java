package com.mintyn.inventory.api.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mintyn.inventory.dto.ProductDTO;
import com.mintyn.inventory.mapper.ProductMapper;
import com.mintyn.inventory.model.CustomerOrder;
import com.mintyn.inventory.model.Product;
import com.mintyn.inventory.model.ShoppingCart;
import com.mintyn.inventory.service.CustomerOrderService;
import com.mintyn.inventory.service.ProductService;
import com.mintyn.inventory.service.ShoppingCartService;
import com.mintyn.inventory.utility.InputValidation;
import com.mintyn.inventory.utility.service.DateTimeService;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryManagementController {
	
	@Autowired private DateTimeService dateTimeService;
	@Autowired private InputValidation inputValidation;
	@Autowired private ProductService productService;
	@Autowired private ProductMapper productMapper;
	@Autowired private CustomerOrderService customerOrderService;
	@Autowired private ShoppingCartService shoppingCartService;
	
	@RequestMapping(value = "/create", method = { RequestMethod.POST }, produces = "application/json")
	public Map<String, Object> addProduct(HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();

			String name = request.getHeader("name");
			String stockCount = request.getHeader("stockCount");
			String price = request.getHeader("price");
			String description = request.getHeader("description");
		
		if (inputValidation.validateAlphanumerice(name).equals(false)) {

			map.put("message", "invalid product name");
			map.put("status", HttpStatus.BAD_REQUEST);

		} else if (inputValidation.validateNumeric(stockCount).equals(false)) {
			
			map.put("message", "invalid stock count");
			map.put("status", HttpStatus.BAD_REQUEST);
			
		} else if (inputValidation.validateNumeric(price).equals(false)) {
			
			map.put("message", "invalid product price");
			map.put("status", HttpStatus.BAD_REQUEST);
			
		} else if (inputValidation.validateAlphanumerice(description).equals(false)) {
			
			map.put("message", "invalid product description");
			map.put("status", HttpStatus.BAD_REQUEST);
			
		} else {
			
			LocalDateTime dateTime = dateTimeService.PresentDateTime();
			
			Product product = new Product();
			
			product.setName(name);
			product.setStockCount(Integer.parseInt(stockCount));
			product.setPrice(Double.parseDouble(price));
			product.setDescription(description);
			product.setCreated(dateTime);
			product.setUpdated(dateTime);
			
			productService.addProduct(product);
			
			map.put("message", "created successful");
			map.put("status", HttpStatus.OK);
			
		}
																	
		return map;
			
	}
	
	@RequestMapping(value = "/update/{productId}", method = { RequestMethod.PUT }, produces = "application/json")
	public Map<String, Object> updateProduct(@PathVariable String productId, HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (inputValidation.validateNumeric(productId).equals(true)) {
			
			String name = request.getHeader("name");
			String stockCount = request.getHeader("stockCount");
			String price = request.getHeader("price");
			String description = request.getHeader("description");
			LocalDateTime dateTime = dateTimeService.PresentDateTime();
			
			Product product = null;
			product = productService.getProductByPk(Long.parseLong(productId));
			
			if (product != null) {
				
				if (inputValidation.validateAlphanumerice(name).equals(true)) 
		
					product.setName(name);		 
					
				if (inputValidation.validateNumeric(stockCount).equals(true)) 
					
					product.setStockCount(product.getStockCount() + Integer.parseInt(stockCount));// Existing stock plus new stock
					
				if (inputValidation.validateNumeric(price).equals(true)) 
					
					product.setPrice(Double.parseDouble(price));
					
				if (inputValidation.validateAlphanumerice(description).equals(true))
					
					product.setDescription(description);
					
				if (product != null)//Re-verify one more time that the product still exist before updating
				
					product.setUpdated(dateTime);					
					productService.updateProduct(product);
					
					map.put("message", "updated successful");
					map.put("status", HttpStatus.OK);										
				
			} else {
				
				map.put("message", "the product with the id " + productId + " does not exist");
				map.put("status", HttpStatus.BAD_REQUEST);
				
			} 
			
		} else {
			
			map.put("message", "invalid product id");
			map.put("status", HttpStatus.BAD_REQUEST);
			
		}
		
		return map;
			
	}

	@RequestMapping(value = "/list/{pageNumber}", method = { RequestMethod.GET }, produces = "application/json")
	public Map<String, Object> listProduct(@PathVariable String pageNumber) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (inputValidation.validateNumeric(pageNumber).equals(false)) {

			map.put("message", "invalid page number");
			map.put("status", HttpStatus.BAD_REQUEST);

		} else if (Integer.parseInt(pageNumber) == 0) {
			
			map.put("message", "page number must be greater than 0");
			map.put("status", HttpStatus.BAD_REQUEST);
			
		} else {
			
			int maxPageSize = 5;
			
			List<ProductDTO> productDTO;
			productDTO = productMapper.mapperFindAllProductPaginated(maxPageSize, Integer.parseInt(pageNumber));
			
			Long productTotal = productService.getProductTotal();
			
			if (productTotal == 0L && productDTO.isEmpty()) {
				
				map.put("message", "we are currently out of stock");
				map.put("status", HttpStatus.SERVICE_UNAVAILABLE);
				
			} else if (productTotal != 0L && productDTO.isEmpty()) {
					
				map.put("message", "requested page number is out of range");
				map.put("status", HttpStatus.SERVICE_UNAVAILABLE);	
				
			} else {
				
				map.put("products", productDTO);
				map.put("message", "successful");
				map.put("status", HttpStatus.OK);				
				
			}
			
		}
		
		return map;
			
	}
	
	@RequestMapping(value = "/order/{productId}", method = { RequestMethod.POST }, produces = "application/json")
	public Map<String, Object> orderProduct(@PathVariable String productId, HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (inputValidation.validateNumeric(productId).equals(true)) {
			
			String name = request.getHeader("name");
			String phone = request.getHeader("phone");
			String quantity = request.getHeader("quantity");
			
			if (inputValidation.validateAlphanumerice(name).equals(false)) {

				map.put("message", "invalid customer name");
				map.put("status", HttpStatus.BAD_REQUEST);

			} else if (inputValidation.validateNumeric(phone).equals(false)) {
				
				map.put("message", "invalid customer phone number");
				map.put("status", HttpStatus.BAD_REQUEST);
				
			} else if (phone.length() < 11 || phone.length() > 11) {
				
				map.put("message", "phone number must be 11 digits");
				map.put("status", HttpStatus.BAD_REQUEST);
				
			} else if (inputValidation.validateNumeric(quantity).equals(false)) {
				
				map.put("message", "invalid order quantity");
				map.put("status", HttpStatus.BAD_REQUEST);
				
			} else if (Integer.parseInt(quantity) < 1) {
				
				map.put("message", "order quantity must be greater than zero");
				map.put("status", HttpStatus.BAD_REQUEST);	
				
			} else {
			
				Product product = null;
				product = productService.getProductByPk(Long.parseLong(productId));
				
				if (product != null) {
					
					if (product.getStockCount() == 0) {//Verify if stock is available
						
						map.put("message", "the requested product out of stock");
						map.put("status", HttpStatus.SERVICE_UNAVAILABLE);
						
					} else if (Integer.parseInt(quantity) > product.getStockCount()) {//Verify if requested quantity is higher than stock 	
					
						map.put("message", "we have only " + product.getStockCount() + " of " + product.getName() + " in stock");
						map.put("status", HttpStatus.SERVICE_UNAVAILABLE);
						
					} else {
						
						//Update Product Stock
						product.setStockCount(product.getStockCount() - Integer.parseInt(quantity));
						productService.updateProduct(product);
						
						//Take the order =========================================================
						
						LocalDate date = dateTimeService.PresentDate();
						LocalDateTime dateTime = dateTimeService.PresentDateTime();
						
						//Create Order
						CustomerOrder customerOrder = new CustomerOrder();						
						customerOrder.setName(name);
						customerOrder.setPhone(phone);
						customerOrder.setAmount(product.getPrice() * Integer.parseInt(quantity));
						customerOrder.setCreated(date);						
						customerOrderService.addOrder(customerOrder);

						//Add Product to Shopping Cart
						ShoppingCart shoppingCart = new ShoppingCart();	
						shoppingCart.setCustomerOrder(customerOrder);
						shoppingCart.setProduct(product);
						shoppingCart.setPrice(product.getPrice());
						shoppingCart.setQuantity(Integer.parseInt(quantity));
						shoppingCart.setCreated(dateTime);
						shoppingCartService.addCart(shoppingCart);
						
						map.put("message", "order placed successful");
						map.put("status", HttpStatus.OK);
						
					}
					
				} else {
					
					map.put("message", "the product with the id " + productId + " does not exist");
					map.put("status", HttpStatus.BAD_REQUEST);
					
				}
			
			}	
			
		} else {
			
			map.put("message", "invalid product id");
			map.put("status", HttpStatus.BAD_REQUEST);
			
		}
		
		return map;
			
	}		
	
}
