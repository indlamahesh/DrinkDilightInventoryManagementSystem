package com.capgemini.inventory.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.inventory.exceptions.ImageException;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.exceptions.InvalidVendorIdException;
import com.capgemini.inventory.exceptions.InventoryTxnException;
import com.capgemini.inventory.exceptions.LoginException;
import com.capgemini.inventory.exceptions.OutOfStockException;
import com.capgemini.inventory.exceptions.UserIdException;
import com.capgemini.inventory.util.InventoryConstants;

@RestControllerAdvice
public class InventoryExceptionAdvice {
	
	Logger logger = LoggerFactory.getLogger(InventoryExceptionAdvice.class);
	
	@ExceptionHandler(value = {InvalidProdIdException.class})
	@ResponseStatus(code= HttpStatus.NOT_FOUND,reason="Unknown Product")
	public void  handleException(Exception ex) {
		logger.error(ex.getMessage());
	}

	@ExceptionHandler(value = {InvalidVendorIdException.class})
	@ResponseStatus(code= HttpStatus.NOT_FOUND,reason="Unknown Vendor")
	public void  handleException2(Exception ex) {
		logger.error(ex.getMessage());	
	}
	
	@ExceptionHandler(value = {OutOfStockException.class, InventoryTxnException.class})
	@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason="out of stock")
	public ErrorInfo  handleExceptio3(Exception ex) {
		logger.error(ex.getMessage());
		ErrorInfo error = new ErrorInfo();
		error.setMessage(ex.getMessage());
		return error;
	}
	
	
	@ExceptionHandler(value = {UserIdException.class})
	@ResponseStatus(code= HttpStatus.BAD_REQUEST,reason=InventoryConstants.USER_ID_ALREADY_EXISTS)
	@ResponseBody
	public void handlerException4(Exception ex) {
		logger.error(ex.getMessage());
	}
	
	@ExceptionHandler(value = {LoginException.class})
	@ResponseStatus(code= HttpStatus.NOT_FOUND,reason=InventoryConstants.USER_NOT_AVAILABLE)
	@ResponseBody
	public void handlerException5(Exception ex) {
		logger.error(ex.getMessage());
	}
	@ExceptionHandler(value = {ImageException.class})
	@ResponseStatus(code= HttpStatus.BAD_REQUEST,reason="image should not larger than 1mb")
	@ResponseBody
	public void handlerException6(Exception ex) {
		logger.error(ex.getMessage());
	}
	
	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	@ResponseBody
	public ErrorInfo handleException7(Exception ex) {
		logger.error(ex.getMessage());
		if(ex.getMessage().contains("doj"))
			return new ErrorInfo("Date must have pattern yyyy-M-d");
		return new ErrorInfo(ex.getMessage());
	}

}