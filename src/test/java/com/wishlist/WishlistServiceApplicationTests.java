package com.wishlist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.service.WishlistDataService;
import com.wishlist.vo.Item;
import com.wishlist.vo.Product;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WishlistServiceApplicationTests {

	List<Item> items = new ArrayList<>();
	
	List<Product> products = new ArrayList<>();
	@Autowired
	MockMvc mockMvc;

	@MockBean
	WishlistDataService dataService;

	@Before
	public void setup() {
		Item mockItem1 = new Item(101, "Shoes", 1000d, "http://dummy/helloShoes.jpg", "Puma Shoes");
		Item mockItem2 = new Item(102, "Mobile", 19000d, "http://dummy/demooMobile.jpg", "Nokia 7.1");
		items.add(mockItem1);
		items.add(mockItem2);
		
		Product product1 =new Product(1001, "Sony Headphone", "Headphone", "In ear type with extra bass", 1500d);
		Product product2 =new Product(1002, "Xiomi Tv", "Television", "Xiomo 42 inches Full HD", 15000d);
		products.add(product1);
		products.add(product2);
	}

	@Test
	public void fetchItems() throws Exception {

		Mockito.when(dataService.fetchWishlistItems()).thenReturn(items);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/wishlist/items").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println(mvcResult.getResponse());
		Mockito.verify(dataService).fetchWishlistItems();
	}
	
	@Test
	public void fetchProducts() throws Exception {

		Mockito.when(dataService.products()).thenReturn(products);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/wishlist/products").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println(mvcResult.getResponse());
		Mockito.verify(dataService).products();
	}

	@Test
	public void testDeleteItem() throws Exception {
		String itemId = "102";
		Item mockItem1 = new Item(102, "Shoes", 1000d, "http://dummy/helloShoes.jpg", "Puma Shoes");
		Mockito.when(dataService.deleteItemFrmWishlist(itemId)).thenReturn(mockItem1);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.delete("/wishlist/deleteItem/" + itemId).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println(mvcResult.getResponse());
		Mockito.verify(dataService).deleteItemFrmWishlist(itemId);
	}

	@Test
	public void testAddItem() throws Exception {
		Item mockItem1 = new Item(101, "Shoes", 1000d, "http://dummy/helloShoes.jpg", "Puma Shoes");
		String inputJson = asJsonString(mockItem1);
		Mockito.when(dataService.addItemToWishlist(Mockito.any(String.class))).thenReturn(mockItem1);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/wishlist/addItem").content(inputJson)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(mvcResult.getResponse());

		String outputJson = mvcResult.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
