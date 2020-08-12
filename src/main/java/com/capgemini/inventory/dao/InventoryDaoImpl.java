package com.capgemini.inventory.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.inventory.entity.Product;
import com.capgemini.inventory.entity.User;
@Repository
public class InventoryDaoImpl implements InventoryDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean addUser(User user) {
		em.persist(user);
		return true;
	}

	@Override
	public boolean editUser(User user) {
		em.merge(user);
		return true;
	}

	@Override
	public User viewUser(String userId) {
		
		return em.find(User.class, userId);
	}
	
	@Override
	public boolean addProduct(Product prod) {
		em.persist(prod);
		return true;
	}

	@Override
	public boolean editProduct(Product prod) {
		em.merge(prod);
		return true;
	}

	@Override
	public Product viewProduct(long prodId) {
		
		return em.find(Product.class, prodId);
	}

	@Override
	public List<Product> viewProducts() {
		String jpql = "from Product ";
		TypedQuery<Product> query = em.createQuery(jpql, Product.class);
		return query.getResultList();
	}
	
	@Override
	public boolean deleteProduct(Product product) {
		em.remove(product);
		return true;
	}

	
	
	}

	

	

