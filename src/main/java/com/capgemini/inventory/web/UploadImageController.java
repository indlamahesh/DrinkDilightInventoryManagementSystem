package com.capgemini.inventory.web;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.inventory.util.InventoryConstants;
import com.capgemini.inventory.exceptions.ImageException;


@RestController
public class UploadImageController {
	
	@Value("${imgpath}")
	private String imgPath;
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@PostMapping("/upload")
	public String uploadImage(@RequestParam("txtfile") MultipartFile file,
			@RequestParam("txtuid")String uid) throws IOException, ImageException {
	
		byte[] arr = file.getBytes();
		FileOutputStream fos = new FileOutputStream(imgPath+uid+InventoryConstants.IMG_TYPE);
				fos.write(arr);
		        fos.close();
		    return InventoryConstants.IMG_UPLOADED;
	}
}