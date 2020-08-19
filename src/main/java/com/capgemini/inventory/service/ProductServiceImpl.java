package com.capgemini.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.inventory.dao.InventoryDao;
import com.capgemini.inventory.entity.Product;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.util.InventoryConstants;




@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private InventoryDao dao;
	
	@Override
	public boolean addProduct(Product prod) {
		
		return dao.addProduct(prod);
	}

	@Override
	public boolean editProduct(Product prod) {
		
		return dao.editProduct(prod);
	}

	@Override
	public Product viewProduct(long prodId) {
		
		return dao.viewProduct(prodId);
	}

	@Override
	public List<Product> viewAllProoducts()  {
		List<Product> prodlst = dao.viewProducts();
		return prodlst;
		
	}

}
