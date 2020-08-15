package com.capgemini.inventory.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.inventory.dao.InventoryDao;
import com.capgemini.inventory.dto.InventoryForm;
import com.capgemini.inventory.entity.InventoryTxn;
import com.capgemini.inventory.entity.Product;
import com.capgemini.inventory.entity.Vendor;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.exceptions.InvalidVendorIdException;
import com.capgemini.inventory.exceptions.OutOfStockException;
import com.capgemini.inventory.util.InventoryConstants;

@Service
@Transactional
public class InventoryTxnServiceImpl implements InventoryTxnService {

	@Autowired
	private InventoryDao dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean addInvTxn(InventoryForm form)
			throws OutOfStockException, InvalidVendorIdException, InvalidProdIdException {

		InventoryTxn txn = new InventoryTxn();

		Vendor vendor = dao.viewVendor(form.getVendorId());
		if (vendor == null || !dao.viewVendors().contains(vendor))
			throw new InvalidVendorIdException(InventoryConstants.INVALID_VENDOR);

		Product prod = dao.viewProduct(form.getProductId());
		if (prod == null || !dao.viewProducts().contains(prod))
			throw new InvalidProdIdException(InventoryConstants.INVALID_PRODUCT);

		if ((txn.getQty() > prod.getStock()) && form.getVendortype().equals(InventoryConstants.CONSUMER))
			throw new OutOfStockException(InventoryConstants.OUT_OF_STOCK);

		long txnId = dao.getMaxTxID() + 1;
		txn.setInventoryId(txnId);
		txn.setProd(prod);
		txn.setVendor(vendor);
		txn.setTxtType(form.getVendortype());
		txn.setDateOfTxn(LocalDate.now());
		txn.setQty(form.getQty());
		if (txn.getVendor().getVendortype().equals(InventoryConstants.SUPPLIER))
			prod.setStock(prod.getStock() + form.getQty());

		else
			prod.setStock(prod.getStock() - form.getQty());

		dao.editProduct(prod);
		dao.addInventory(txn);
		return true; // Order placed Successfully
	}

	@Override
	public List<InventoryTxn> txnOfSpecVendor(long vendorId) throws InvalidVendorIdException {

		List<InventoryTxn> txnlst = dao.viewInventory(vendorId);
		txnlst.sort((t1, t2) -> t2.getDateOfTxn().compareTo(t1.getDateOfTxn()));
		if (txnlst.isEmpty())
			throw new InvalidVendorIdException(InventoryConstants.NO_TRANSACTIONS);
		return txnlst;
	}

	@Override
	public List<Vendor> viewAllVendors() {
		List<Vendor> vendorList = dao.viewVendors();
		vendorList.sort((v1, v2) -> v1.getCompanyName().compareTo(v2.getCompanyName()));
		return vendorList;
	}

}