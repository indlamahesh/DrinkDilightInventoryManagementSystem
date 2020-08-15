package com.capgemini.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.inventory.dao.InventoryDao;
import com.capgemini.inventory.entity.InventoryTxn;
import com.capgemini.inventory.entity.Product;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.exceptions.InventoryTxnException;
import com.capgemini.inventory.util.InventoryConstants;

@Service
public  class SalesandPurchaseReportServiceImpl implements SalesandPurchaseReportService {

	@Autowired
	private InventoryDao dao;
	
	@Override
	public List<InventoryTxn> viewSalesNPurchaseReport(String vendorType) throws InventoryTxnException   {
		List<InventoryTxn> list = dao.viewInventoryForVendorType(vendorType);
		if(list.isEmpty())
			throw new InventoryTxnException(InventoryConstants.NO_TRANSACTIONS);
		list.sort((t1, t2) ->t2.getDateOfTxn().compareTo(t1.getDateOfTxn()));
		return list;
	}

	@Override
	public List<Product> searchInventory(String searchStr) throws InvalidProdIdException {
		List<Product> lst = dao.viewInventory(searchStr);
		if (lst.isEmpty())
			throw new InvalidProdIdException(InventoryConstants.NO_PRODUCTS);
		return null;
	}

	
	

}
