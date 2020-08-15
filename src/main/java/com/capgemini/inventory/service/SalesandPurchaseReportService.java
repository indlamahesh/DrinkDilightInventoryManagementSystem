package com.capgemini.inventory.service;

import java.util.List;

import com.capgemini.inventory.entity.InventoryTxn;
import com.capgemini.inventory.entity.Product;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.exceptions.InventoryTxnException;

public interface SalesandPurchaseReportService {

	public List<InventoryTxn> viewSalesNPurchaseReport(String vendorType) throws InventoryTxnException;

	public List<Product> searchInventory(String searchStr) throws InvalidProdIdException;

}
