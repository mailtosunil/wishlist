package com.wishlist.test.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import com.wishlist.service.WishlistDataService;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WishlistDataServiceTest {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	WishlistDataService dataService;
	
	private MockRestServiceServer mockServer;
	
	@Before
	public void setup() {
		RestGatewaySupport gateway = new RestGatewaySupport();
		gateway.setRestTemplate(restTemplate);
		mockServer = MockRestServiceServer.createServer(gateway);
	}
	
	@Test
	public void testFetchWishlistItems() {
		//mockServer.expect(once(), requestTo("")).andRespond(responseCreator);;
	}
}
