package com.capgemini.inventory.service;

import java.time.LocalDate;
import java.util.List;
import com.capgemini.inventory.entity.InventoryTxn;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.exceptions.InventoryTxnException;

public interface InventoryService {
	
	public List<InventoryTxn> viewSuppliersForProductId(long productId) throws InvalidProdIdException;
	public List<InventoryTxn> viewConsumersForProductId(long productId) throws InvalidProdIdException;
	public List<InventoryTxn> viewSalesRepoForDateRange(LocalDate fromDt , LocalDate toDate)throws InventoryTxnException;
	public List<InventoryTxn> viewPurchaseRepoForDateRange(LocalDate fromDt , LocalDate toDate)throws InventoryTxnException;

}
