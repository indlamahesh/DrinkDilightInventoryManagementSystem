package com.capgemini.inventory.service;


import com.capgemini.inventory.exceptions.LoginException;
import com.capgemini.inventory.entity.User;

public interface LoginService {
    public User doLogin(String userId, String password)throws LoginException;
    public String encryptUser(User user);
    
}
