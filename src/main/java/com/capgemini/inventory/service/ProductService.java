package com.capgemini.inventory.service;

import java.util.List;

import com.capgemini.inventory.entity.Product;
import com.capgemini.inventory.exceptions.InvalidProdIdException;

public interface ProductService {

	public boolean addProduct(Product prod);
	public boolean editProduct(Product prod);
	public Product viewProduct(long prodId);
	public List<Product> viewAllProoducts();
}
