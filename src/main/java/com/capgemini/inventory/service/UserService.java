package com.capgemini.inventory.service;

import com.capgemini.inventory.entity.User;

public interface UserService {

	public boolean addUser(User user);
	public User viewUser(String userId);
	public boolean editUser(User user);
}
