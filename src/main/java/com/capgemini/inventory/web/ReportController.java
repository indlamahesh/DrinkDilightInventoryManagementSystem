package com.capgemini.inventory.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.inventory.entity.InventoryTxn;
import com.capgemini.inventory.exceptions.InventoryTxnException;
import com.capgemini.inventory.service.SalesandPurchaseReportService;
import com.capgemini.inventory.util.InventoryConstants;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

	@Autowired
	private SalesandPurchaseReportService ser;
	
	@GetMapping("/viewsalesreport")
	public List<InventoryTxn> getSalesReport() throws InventoryTxnException   {
		return ser.viewSalesNPurchaseReport(InventoryConstants.CONSUMER);
	}
	
	
	@GetMapping("/viewpurchasereport")
	public List<InventoryTxn> getPurchaseReport() throws InventoryTxnException  {
		return ser.viewSalesNPurchaseReport(InventoryConstants.SUPPLIER);
	}
	
}
