package com.capgemini.inventory.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.inventory.entity.Product;
import com.capgemini.inventory.entity.User;
import com.capgemini.inventory.entity.InventoryTxn;
import com.capgemini.inventory.entity.Vendor;

public interface InventoryDao {
	public boolean addUser(User user);
	public boolean editUser(User user);
	public User viewUser(String userId);
	
	
	public boolean addProduct(Product prod);
	public boolean editProduct(Product prod);
	public Product viewProduct(long prodId);
	public List<Product> viewProducts();

	
	

	public boolean addVendor(Vendor vendor);
	public boolean editVendor(Vendor vendor);	
	public Vendor viewVendor(long vendorId);
	public List<Vendor> viewVendor(String vendorType);
	public List<Vendor> viewVendors();
	
	
	
	public boolean addInventory(InventoryTxn inventory);
	public List<InventoryTxn> viewInventory(long vendorId);
	public List<InventoryTxn> viewInventoryForVendorType(String vendorType);
	public List<InventoryTxn> viewInventoryForProductId(long productId, String vendorType);
	public List<InventoryTxn> viewInventory(LocalDate fromDt, LocalDate toDt, String vendorType);
	public List<Product> viewInventory(String searchStr);
	
	public long getMaxTxID();
	
	
}







