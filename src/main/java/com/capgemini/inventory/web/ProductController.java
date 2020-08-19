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

	@CrossOrigin(origins = { "http://localhost:4200" })
	@PostMapping("/addproduct")
	public InventoryMessage addProduct(@RequestBody Product product) throws InvalidProdIdException {

		try {

			service.addProduct(product);
			InventoryMessage msg = new InventoryMessage();
			msg.setMessage(InventoryConstants.ADDED);
			return msg;

		} catch (DataIntegrityViolationException ex) {
			throw new InvalidProdIdException(InventoryConstants.UNSUCCESFULL);
		}

	}

	@CrossOrigin(origins = { "http://localhost:4200" })
	@PutMapping("/editproduct")
	public InventoryMessage editProduct(@RequestBody Product product) throws InvalidProdIdException {

		service.editProduct(product);
		InventoryMessage msg = new InventoryMessage();
		msg.setMessage(InventoryConstants.EDITED);
		return msg;
	}

	@CrossOrigin(origins = { "http://localhost:4200" })
	@GetMapping("/getproduct/{productId}")
	public Product viewProductById(@PathVariable("productId") long productId)
			throws InvalidProdIdException {
			return service.viewProduct(productId);
		

	}

	@CrossOrigin(origins = { "http://localhost:4200" })
	@GetMapping("/viewallproducts")
	public List<Product> viewProducts() {
		return service.viewAllProoducts();

	}

}
