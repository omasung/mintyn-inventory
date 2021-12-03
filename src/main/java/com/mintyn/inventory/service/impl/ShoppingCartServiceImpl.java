package com.mintyn.inventory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mintyn.inventory.dao.ShoppingCartDAO;
import com.mintyn.inventory.model.ShoppingCart;
import com.mintyn.inventory.service.ShoppingCartService;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired private ShoppingCartDAO shoppingCartDAO;

	@Override
	public void addCart(ShoppingCart shoppingCart) {

		shoppingCartDAO.addCart(shoppingCart);
	}

	@Override
	public void addOrUpdateCart(ShoppingCart shoppingCart) {

		shoppingCartDAO.addOrUpdateCart(shoppingCart);
	}

	@Override
	public void updateCart(ShoppingCart shoppingCart) {

		shoppingCartDAO.updateCart(shoppingCart);
	}

	@Override
	public ShoppingCart getCartByPk(Long shoppingCartId) {

		return shoppingCartDAO.getCartByPk(shoppingCartId);
	}

	@Override
	public List<ShoppingCart> getAllCart() {

		return shoppingCartDAO.getAllCart();
	}

	@Override
	public void deleteCart(Long shoppingCartId) {

		shoppingCartDAO.deleteCart(shoppingCartId);
	}

}
