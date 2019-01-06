package com.wishlist.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Item> fetchWishlistItems() {
		LOG.info("Execution starts: fetchWishlistItems");
		ResponseEntity<List<Item>> reponseItems = restTemplate.exchange(
				"http://localhost:9090/wishlist-data-service/data/items", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Item>>() {
				});
		LOG.info("Execution ends: fetchWishlistItems");
		return reponseItems.getBody();
	}

	@Override
	public Item addItemToWishlist(String prodId) {
		LOG.info("Execution starts: addItemToWishlist");
		ResponseEntity<Item> reponseOfAdd = restTemplate
				.postForEntity("http://localhost:9090/wishlist-data-service/data/add/item", prodId, Item.class);
		Item itemEnt = reponseOfAdd.getBody();
		LOG.info("Execution ends: addItemToWishlist");
		return itemEnt;
	}

	@Override
	public Item deleteItemFrmWishlist(String itemId) {
		LOG.info("Execution starts: deleteItemFrmWishlist");
		ResponseEntity<Item> reponseOfDelete = restTemplate
				.postForEntity("http://localhost:9090/wishlist-data-service/data/delete/item", itemId, Item.class);
		Item itemEnt = reponseOfDelete.getBody();
		LOG.info("Execution ends: deleteItemFrmWishlist");
		return itemEnt;
	}

	@Override
	public List<Product> products() {
		LOG.info("Execution starts: products");
		ResponseEntity<List<Product>> reponseItems = restTemplate.exchange(
				"http://localhost:9090/wishlist-data-service/products", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Product>>() {
				});
		LOG.info("Execution ends: products");
		return reponseItems.getBody();
	}
}
