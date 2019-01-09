package com.wishlist.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wishlist.service.WishlistDataService;
import com.wishlist.vo.Item;
import com.wishlist.vo.Product;

@Service
public class WishlistDataServiceImpl implements WishlistDataService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Item> fetchWishlistItems() {
		ResponseEntity<List<Item>> reponseItems = restTemplate.exchange(
				"http://localhost:9090/wishlist-data-service/data/items", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Item>>() {
				});
		return reponseItems.getBody();
	}

	@Override
	public Item addItemToWishlist(String prodId) {
		ResponseEntity<Item> reponseOfAdd = restTemplate
				.postForEntity("http://localhost:9090/wishlist-data-service/data/add/item", prodId, Item.class);
		return reponseOfAdd.getBody();
	}

	@Override
	public Item deleteItemFrmWishlist(String itemId) {
		ResponseEntity<Item> reponseOfDelete = restTemplate
				.postForEntity("http://localhost:9090/wishlist-data-service/data/delete/item", itemId, Item.class);
		return reponseOfDelete.getBody();
	}

	@Override
	public List<Product> products() {
		ResponseEntity<List<Product>> reponseItems = restTemplate.exchange(
				"http://localhost:9090/wishlist-data-service/products", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Product>>() {
				});
		return reponseItems.getBody();
	}
}
