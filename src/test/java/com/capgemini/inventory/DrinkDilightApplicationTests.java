package com.capgemini.inventory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.inventory.dao.InventoryDao;
import com.capgemini.inventory.entity.Product;
import com.capgemini.inventory.exceptions.InvalidProdIdException;
import com.capgemini.inventory.service.ProductService;
import com.capgemini.inventory.service.ProductServiceImpl;

@SpringBootTest
class DrinkDilightApplicationTests {

	@InjectMocks
	private ProductService productService = new ProductServiceImpl();

	@Mock
	private InventoryDao productRepository;

	private static Product product1, product2;

	@BeforeAll
	public static void beforeAll() {
		product1 = new Product();
		product1.setProductId(1001);
		product1.setBrand("Acer");
		product1.setProductModel("I-series");
		product1.setProductName("Acer X50 Laptop");
		product1.setStock(23);
		product1.setSellerPrice(36000);
		product1.setSupplierPrice(32000);

		product2 = new Product();
		product2.setProductId(1002);
		product2.setBrand("Dell");
		product2.setProductModel("Z-series");
		product2.setProductName("Dell 50x Laptop");
		product2.setSellerPrice(40000);
		product2.setStock(23);
		product2.setSupplierPrice(35000);

	}

	@Test
	public void shouldAddProductSuccesfully() {
		when(productRepository.addProduct(product1)).thenReturn(true);
		assertEquals(true, productService.addProduct(product1));

	}

	@Test
	public void getProductById() {
		when(productRepository.viewProduct(1001)).thenReturn(product1);
		Product id = productService.viewProduct(1001);
		assertEquals(product1, id);
	}

	@Test
	public void testGetAllProducts() throws InvalidProdIdException {
		List<Product> productList = new ArrayList<>();
		productList.add(product1);
		productList.add(product2);
		Mockito.when(productRepository.viewProducts()).thenReturn(productList);
		assertThat(productService.viewAllProoducts()).isEqualTo(productList);

	}

	@Test
	public void GetSizeOfAllProducts() throws InvalidProdIdException {
		List<Product> productList = new ArrayList<>();
		productList.add(product1);
		productList.add(product2);
		Mockito.when(productRepository.viewProducts()).thenReturn(productList);
		assertEquals(2, productService.viewAllProoducts().size());

	}

	@Test
	public void editProductWhenProductIsPresnetTest() {
		product1.setBrand("Apple");
		when(productRepository.editProduct(product1)).thenReturn(true);
		boolean updateResult = productService.editProduct(product1);
		assertEquals(updateResult, productService.editProduct(product1));
	}
}
