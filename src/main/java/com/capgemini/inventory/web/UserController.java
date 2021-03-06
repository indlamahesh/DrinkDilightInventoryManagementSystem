package com.capgemini.inventory.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.inventory.entity.User;
import com.capgemini.inventory.exceptions.LoginException;
import com.capgemini.inventory.exceptions.UserIdException;
import com.capgemini.inventory.service.UserService;
import com.capgemini.inventory.util.InventoryConstants;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@CrossOrigin(origins = { "http://localhost:4200" })
	@PostMapping("/adduser")
	public String addUser(@RequestBody User user) throws UserIdException {
		try {
			service.addUser(user);
			return InventoryConstants.ADDED;
		} catch (DataIntegrityViolationException ex) {
			throw new UserIdException(InventoryConstants.USER_ID_ALREADY_EXISTS);
		}
	}

	@CrossOrigin(origins = { "http://localhost:4200" })
	@GetMapping("/viewuser/{userId}")
	public User viewUser(HttpServletRequest req, @PathVariable("userId") String userId) throws LoginException {

		 return service.viewUser(userId);
	}

	@CrossOrigin(origins = { "http://localhost:4200" })
	@PutMapping("/edituser")
	public String edituser(HttpServletRequest req, @RequestBody User user) throws LoginException {
		service.editUser(user);
		return InventoryConstants.EDITED;

	}

}
