package com.capgemini.inventory.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.inventory.entity.InventoryTxn;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.exceptions.InventoryTxnException;
import com.capgemini.inventory.service.InventoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VendorController {
	
	@Autowired
	private InventoryService service;

	@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/salesreport/{productid}")
	public List<InventoryTxn> viewSalesReport(@PathVariable("productid")int productId) throws InvalidProdIdException   {
		List<InventoryTxn> consumerList = service.viewConsumersForProductId(productId);
		
		return consumerList;
	}

	@CrossOrigin(origins= {"http://localhost:4200"})	
	@GetMapping("/purchasereport/{productid}")
	public List<InventoryTxn> viewPurchaseReport(@PathVariable("productid")int productId) throws InvalidProdIdException   {
		List<InventoryTxn> supplierList = service.viewSuppliersForProductId(productId);
		
		return supplierList;
	}
	
	@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/purchasereportdate/{fromdt}/{todt}")
	public List<InventoryTxn> viewPurchaseReportForDateRange(@PathVariable("fromdt") @DateTimeFormat(pattern="yyyy-M-d") LocalDate fromDt, @PathVariable("todt") @DateTimeFormat(pattern="yyyy-M-d") LocalDate toDate ) throws InventoryTxnException   {
		List<InventoryTxn> supplierList = service.viewPurchaseRepoForDateRange(fromDt, toDate);
		
		return supplierList;
	}
	
	@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/salesreportdate/{fromdt}/{todt}")
	public List<InventoryTxn> viewSaleseReportForDateRange(@PathVariable("fromdt") @DateTimeFormat(pattern="yyyy-M-d") LocalDate fromDt, @PathVariable("todt") @DateTimeFormat(pattern="yyyy-M-d") LocalDate toDate ) throws InventoryTxnException   {
		List<InventoryTxn> salesList = service.viewSalesRepoForDateRange(fromDt, toDate);
		
		return salesList;
	}
	


}