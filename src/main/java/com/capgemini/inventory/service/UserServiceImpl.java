package com.capgemini.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.inventory.dao.InventoryDao;
import com.capgemini.inventory.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private InventoryDao dao;
	
	
	@Override
	@Transactional
	public boolean addUser(User user) {
		
		return dao.addUser(user);
	}

	@Override
	public User viewUser(String userId) {
		
		return dao.viewUser(userId);
		
	}
	@Override
	public boolean editUser(User user) {
		
		return dao.editUser(user);
	}

}
