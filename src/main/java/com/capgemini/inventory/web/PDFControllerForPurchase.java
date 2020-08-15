package com.capgemini.inventory.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.inventory.dao.InventoryDao;
import com.capgemini.inventory.entity.InventoryTxn;
import com.capgemini.inventory.util.InventoryConstants;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PDFControllerForPurchase {

	@Autowired
	private InventoryDao dao;

	@CrossOrigin
	@GetMapping("/viewpdfforpurchase")
	public void downloadPdfPurchase(HttpServletResponse resp) {
		List<InventoryTxn> lst = dao.viewInventoryForVendorType(InventoryConstants.SUPPLIER);
		Document document = new Document();
		try {
			// resp.setHeader("Content-Disposition", "attachement");
			PdfWriter writer = PdfWriter.getInstance(document, resp.getOutputStream());
			document.open();
			document.add(new Paragraph("Total Purchase Report"));

			PdfPTable table = new PdfPTable(10); // 3 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			// Set Column widths
			// float[] columnWidths = {1f, 1f, 1f, 1f};
			// table.setWidths(columnWidths);

			PdfPCell cell1 = new PdfPCell(new Paragraph("Date Of Txn"));
			PdfPCell cell2 = new PdfPCell(new Paragraph("inventory Id"));
			PdfPCell cell3 = new PdfPCell(new Paragraph("Product ID"));
			PdfPCell cell4 = new PdfPCell(new Paragraph("vendor Type"));
			PdfPCell cell5 = new PdfPCell(new Paragraph("Produt Name"));
			PdfPCell cell6 = new PdfPCell(new Paragraph("Product Model"));
			PdfPCell cell7 = new PdfPCell(new Paragraph("Product Brand"));
			PdfPCell cell8 = new PdfPCell(new Paragraph("Vendor"));
			PdfPCell cell9 = new PdfPCell(new Paragraph("Quantity"));
			PdfPCell cell10 = new PdfPCell(new Paragraph("Contact No"));

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			table.addCell(cell8);
			table.addCell(cell9);
			table.addCell(cell10);
			for (InventoryTxn txn : lst) {
				cell1 = new PdfPCell(new Paragraph((txn.getDateOfTxn().toString())));
				cell2 = new PdfPCell(new Paragraph(txn.getInventoryId() + ""));
				cell3 = new PdfPCell(new Paragraph(txn.getProd().getProductId() + ""));
				cell4 = new PdfPCell(new Paragraph(txn.getVendor().getVendortype()));
				cell5 = new PdfPCell(new Paragraph(txn.getProd().getProductName()));
				cell6 = new PdfPCell(new Paragraph(txn.getProd().getProductModel()));
				cell7 = new PdfPCell(new Paragraph(txn.getProd().getBrand()));
				cell8 = new PdfPCell(new Paragraph(txn.getVendor().getVendortype()));
				cell9 = new PdfPCell(new Paragraph(txn.getQty() + ""));
				cell10 = new PdfPCell(new Paragraph(txn.getVendor().getContact()));
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
				table.addCell(cell5);
				table.addCell(cell6);
				table.addCell(cell7);
				table.addCell(cell8);
				table.addCell(cell9);
				table.addCell(cell10);
			}
			document.add(table);

			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
