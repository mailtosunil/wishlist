package com.wishlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
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

		Product product1 = new Product(1001, "Sony Headphone", "Headphone", "In ear type with extra bass", 1500d);
		Product product2 = new Product(1002, "Xiomi Tv", "Television", "Xiomo 42 inches Full HD", 15000d);
		products.add(product1);
		products.add(product2);
	}

	@Test
	public void fetchItems() throws Exception {

		Mockito.when(dataService.fetchWishlistItems()).thenReturn(items);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/wishlist/items").accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(mvcResult.getResponse());
		Mockito.verify(dataService).fetchWishlistItems();
	}

	@Test
	public void testFetchItems_empty() throws Exception {
		List<Item> results = new ArrayList<>();
		Mockito.when(dataService.fetchWishlistItems()).thenReturn(results);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/wishlist/items").accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(mvcResult.getResponse());
		assertNotNull(mvcResult);
		assertEquals(404, mvcResult.getResponse().getStatus());
		Mockito.verify(dataService).fetchWishlistItems();
	}
	
	@Test
	public void testFetchItems_null() throws Exception {
		List<Item> results = null;
		Mockito.when(dataService.fetchWishlistItems()).thenReturn(results);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/wishlist/items").accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(mvcResult.getResponse());
		assertNotNull(mvcResult);
		assertEquals(404, mvcResult.getResponse().getStatus());
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
	public void testFetchProducts_empty() throws Exception {
		List<Product> results = new ArrayList<>();
		Mockito.when(dataService.products()).thenReturn(results);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/wishlist/products").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println(mvcResult.getResponse());
		assertNotNull(mvcResult);
		assertEquals(404, mvcResult.getResponse().getStatus());
		Mockito.verify(dataService).products();
	}
	
	@Test
	public void testFetchProducts_null() throws Exception {
		List<Product> results = null;
		Mockito.when(dataService.products()).thenReturn(results);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/wishlist/products").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println(mvcResult.getResponse());
		assertNotNull(mvcResult);
		assertEquals(404, mvcResult.getResponse().getStatus());
		Mockito.verify(dataService).products();
	}

	@Test
	public void testDeleteItem() throws Exception {
		String itemId = "1002";
		Item mockItem1 = new Item(102, "Shoes", 1000d, "http://dummy/helloShoes.jpg", "Puma Shoes");
		Mockito.when(dataService.deleteItemFrmWishlist(Mockito.any(String.class))).thenReturn(mockItem1);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.delete("/wishlist/deleteItem/" + itemId).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println(mvcResult.getResponse());
		Mockito.verify(dataService).deleteItemFrmWishlist(itemId);
	}
	
	@Test
	public void testDeleteItem_null() throws Exception {
		String itemId = "1002";
		Item mockItem1 = null;
		Mockito.when(dataService.deleteItemFrmWishlist(Mockito.any(String.class))).thenReturn(mockItem1);

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.delete("/wishlist/deleteItem/" + itemId).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		String outputJson = mvcResult.getResponse().getContentAsString();
		System.out.println("------> "+outputJson);
		assertEquals(HttpStatus.NOT_MODIFIED.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void testAddItem() throws Exception {
		String prodId = "102";
		Item mockItem1 = new Item(102, "Shoes", 1000d, "http://dummy/helloShoes.jpg", "Puma Shoes");
		Mockito.when(dataService.addItemToWishlist(Mockito.any(String.class))).thenReturn(mockItem1);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/wishlist/addItem").content(prodId)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(mvcResult.getResponse());

		String outputJson = mvcResult.getResponse().getContentAsString();
		Map<String, Object> resultAsObject = asJavaObject(outputJson);
		System.out.println("------> "+resultAsObject);
		assertEquals(102, resultAsObject.get("id"));
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void testAddItem_null() throws Exception {
		String prodId = "102";
		Item mockItem1 = null;
		Mockito.when(dataService.addItemToWishlist(Mockito.any(String.class))).thenReturn(mockItem1);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/wishlist/addItem").content(prodId)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		String outputJson = mvcResult.getResponse().getContentAsString();
		System.out.println("------> "+outputJson);
		assertEquals(HttpStatus.NOT_MODIFIED.value(), mvcResult.getResponse().getStatus());
	}
	
	@Ignore
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Ignore
	public static Map<String, Object> asJavaObject(final String jsonString) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			final Map<String, Object> objectContent = mapper.readValue(jsonString, Map.class);
			return objectContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
