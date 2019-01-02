package com.wishlist;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wishlist.model.Item;
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
	public void fetchWishlistItems_success() throws Exception {
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
	
	/*@Test(expected=ServiceException.class)
	public void fetchWishlistItems_failure()  {
		try {
			Mockito.when(dataService.fetchWishlistItems()).thenThrow(ServiceException.class);			
			MvcResult mvcResult = mockMvc
					.perform(MockMvcRequestBuilders.get("/api/wishlist/items").accept(MediaType.APPLICATION_JSON))
					.andReturn();
			System.out.println(mvcResult.getResponse());
			Mockito.verify(dataService).fetchWishlistItems();
		} catch (ServiceException e) {
			
		} catch (Exception e) {
			
		}
	}*/
}
