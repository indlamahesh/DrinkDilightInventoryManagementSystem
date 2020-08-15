package com.capgemini.inventory.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.inventory.dto.InventoryForm;
import com.capgemini.inventory.dto.InventoryMessage;
import com.capgemini.inventory.entity.InventoryTxn;
import com.capgemini.inventory.entity.Vendor;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.exceptions.InvalidVendorIdException;
import com.capgemini.inventory.exceptions.OutOfStockException;
import com.capgemini.inventory.service.InventoryTxnService;
import com.capgemini.inventory.util.InventoryConstants;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InventoryRestController {

	@Autowired
	public InventoryTxnService ser;


	@PostMapping("/addinventorytxn")
	public InventoryMessage addInventoryTxn(@RequestBody InventoryForm form) throws OutOfStockException, 
																		InvalidVendorIdException, InvalidProdIdException {
		
		
			ser.addInvTxn(form);
			InventoryMessage msg = new InventoryMessage();
			msg.setMessage(InventoryConstants.SUCCESS_TXN);
		    return msg;
	}

	
	@GetMapping("/txnofvendor/{vendorId}")
	public List<InventoryTxn> viewTxnsOfSpecVendor(@PathVariable("vendorId") long vendorId)
			throws InvalidVendorIdException {
		List<InventoryTxn> txnlst = ser.txnOfSpecVendor(vendorId);
		
		return txnlst;
	}
	

	@GetMapping("/viewallVendors")
	public List<Vendor> viewAllVendors() {
		return ser.viewAllVendors();
	}
}
