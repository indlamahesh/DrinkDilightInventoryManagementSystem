package com.capgemini.inventory.service;

import java.util.List;

import com.capgemini.inventory.dto.InventoryForm;
import com.capgemini.inventory.entity.InventoryTxn;
import com.capgemini.inventory.entity.Vendor;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.exceptions.InvalidVendorIdException;
import com.capgemini.inventory.exceptions.OutOfStockException;

public interface InventoryTxnService {

	public boolean addInvTxn(InventoryForm form)
			throws OutOfStockException, InvalidVendorIdException, InvalidProdIdException;

	public List<Vendor> viewAllVendors();

	public List<InventoryTxn> txnOfSpecVendor(long vendorId) throws InvalidVendorIdException;
}
