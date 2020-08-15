package com.capgemini.inventory.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.inventory.dto.InventoryMessage;
import com.capgemini.inventory.entity.Product;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.exceptions.LoginException;
import com.capgemini.inventory.service.ProductService;
import com.capgemini.inventory.util.InventoryConstants;


@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@PostMapping("/addproduct")
	public InventoryMessage addProduct(@RequestBody Product product, HttpServletRequest req
			) throws InvalidProdIdException,UserException, LoginException {

		try {
		if((boolean)req.getAttribute("authFlag")) {
		service.addProduct(product);
		InventoryMessage msg = new InventoryMessage();
		msg.setMessage(InventoryConstants.ADDED);
		return msg;}
		throw new LoginException(InventoryConstants.NOT_AUTHENTICATED);
		}catch (DataIntegrityViolationException ex) {
			throw new InvalidProdIdException(InventoryConstants.UNSUCCESFULL);
		}
		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"}) 
	@PutMapping("/editproduct")
	public InventoryMessage editProduct(HttpServletRequest req,  @RequestBody Product product) throws LoginException {
		if((boolean)req.getAttribute("authFlag")) {
		service.editProduct(product);
		InventoryMessage msg = new InventoryMessage();
		msg.setMessage(InventoryConstants.EDITED);
		return msg;}
		throw new LoginException();
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping("/getproduct/{productid}")
	public Product viewProductById(HttpServletRequest req, @PathVariable("productid") long productId) throws 
	InvalidProdIdException {
		 if((boolean)req.getAttribute("authFlag"))
		return service.viewProduct(productId);
			throw new InvalidProdIdException(InventoryConstants.INVALID_PRODUCT);
		
				
	
	}
		
	@CrossOrigin(origins = {"http://localhost:4200"}) 
	@GetMapping("/viewallproduct")
	public List<Product> viewProducts(HttpServletRequest req) throws InvalidProdIdException{
			return service.viewAllProoducts();
			
		
	}
	

}
