package com.wishlist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
import com.wishlist.vo.ItemForm;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WishlistServiceApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	WishlistDataService dataService;

	@Test
	public void fetchItems() throws Exception {
		List<ItemForm> items = new ArrayList<>();
		ItemForm mockItem1 = new ItemForm(101, "Shoes", 1000d, "http://dummy/helloShoes.jpg", "Puma Shoes");
		ItemForm mockItem2 = new ItemForm(102, "Mobile", 19000d, "http://dummy/demooMobile.jpg", "Nokia 7.1");
		items.add(mockItem1);
		items.add(mockItem2);

		Mockito.when(dataService.fetchWishlistItems()).thenReturn(items);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/wishlist/items").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println(mvcResult.getResponse());
		Mockito.verify(dataService).fetchWishlistItems();
	}

	@Test
	public void testDeleteItem() throws Exception {
		String itemId = "102";
		ItemForm mockItem1 = new ItemForm(102, "Shoes", 1000d, "http://dummy/helloShoes.jpg", "Puma Shoes");
		Mockito.when(dataService.deleteItemFrmWishlist(itemId)).thenReturn(mockItem1);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/wishlist/deleteItem/" + itemId).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println(mvcResult.getResponse());
		Mockito.verify(dataService).deleteItemFrmWishlist(itemId);
	}

	@Test
	public void testAddItem() throws Exception {
		ItemForm mockItem1 = new ItemForm(101, "Shoes", 1000d, "http://dummy/helloShoes.jpg", "Puma Shoes");
		String inputJson = asJsonString(mockItem1);
		Mockito.when(dataService.addItemToWishlist(Mockito.any(ItemForm.class))).thenReturn(mockItem1);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/wishlist/addItem").content(inputJson)
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
