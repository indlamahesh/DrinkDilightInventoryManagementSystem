package com.capgemini.inventory.dao;

import java.util.List;

import com.capgemini.inventory.entity.Product;
import com.capgemini.inventory.entity.User;

public interface InventoryDao {
	public boolean addUser(User user);
	public boolean editUser(User user);
	public User viewUser(String userId);
	
	
	public boolean addProduct(Product prod);
	public boolean editProduct(Product prod);
	public Product viewProduct(long prodId);
	public List<Product> viewProducts();
	public boolean deleteProduct(Product product);
	
	

	
	
	
}







