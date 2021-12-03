package com.mintyn.inventory.dao;

import java.util.List;

import com.mintyn.inventory.model.ShoppingCart;

public interface ShoppingCartDAO {

	void addCart(ShoppingCart shoppingCart);

	void addOrUpdateCart(ShoppingCart shoppingCart);

	void updateCart(ShoppingCart shoppingCart);

	ShoppingCart getCartByPk(Long shoppingCartId);

	List<ShoppingCart> getAllCart();

	void deleteCart(Long shoppingCartId);

}
