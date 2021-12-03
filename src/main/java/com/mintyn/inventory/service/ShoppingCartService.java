package com.mintyn.inventory.service;

import java.util.List;

import com.mintyn.inventory.model.ShoppingCart;

public interface ShoppingCartService {

	void addCart(ShoppingCart shoppingCart);

	void addOrUpdateCart(ShoppingCart shoppingCart);

	void updateCart(ShoppingCart shoppingCart);

	ShoppingCart getCartByPk(Long shoppingCartId);

	List<ShoppingCart> getAllCart();

	void deleteCart(Long shoppingCartId);
	
}
