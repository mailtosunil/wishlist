package com.wishlist.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.wishlist.service.WishlistDataService;
import com.wishlist.vo.Item;
import com.wishlist.vo.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class WishlistDataServiceTest {
	
	List<Item> items = new ArrayList<>();

	List<Product> products = new ArrayList<>();
	
	@LocalServerPort
	int randomServerPort;
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	WishlistDataService dataService;
	
	
	@Before
	public void setup() {
		Item mockItem1 = new Item(101, "Shoes", 1000d, "http://dummy/helloShoes.jpg", "Puma Shoes");
		Item mockItem2 = new Item(102, "Mobile", 19000d, "http://dummy/demooMobile.jpg", "Nokia 7.1");
		items.add(mockItem1);
		items.add(mockItem2);

		Product product1 = new Product(1001, "Sony Headphone", "Headphone", "In ear type with extra bass", 1500d);
		Product product2 = new Product(1002, "Xiomi Tv", "Television", "Xiomo 42 inches Full HD", 15000d);
		products.add(product1);
		products.add(product2);
	}
	
	@Test
	public void testFetchWishlistItems() throws Exception{
		Mockito.when(restTemplate.getForEntity("", List.class)).thenReturn(new ResponseEntity<>(items, HttpStatus.OK));
	}
}
