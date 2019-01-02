package com.wishlist.service.impl;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wishlist.WishlistUtility;
import com.wishlist.model.Item;
import com.wishlist.service.WishlistDataService;
import com.wishlist.vo.ItemForm;

@Service
public class WishlistDataServiceImpl implements WishlistDataService{
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<ItemForm> fetchWishlistItems() {
		LOG.info("Execution starts: fetchWishlistItems");
		ResponseEntity<List<Item>> reponseItems = restTemplate.exchange(
				"http://localhost:9090/wishlist-data-service/wishlist/items", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Item>>() {
				});
		LOG.info("Execution ends: fetchWishlistItems");
		return WishlistUtility.populateItems(reponseItems.getBody());
	}
	
	@Override
	public ItemForm addItemToWishlist(ItemForm itemForm) {
		LOG.info("Execution starts: addItemToWishlist");
		ItemForm formdata = new ItemForm();
		ResponseEntity<Item> reponseOfAdd = restTemplate
				.postForEntity("http://localhost:9090/wishlist-data-service/wishlist/add", itemForm, Item.class);
		Item itemEnt = reponseOfAdd.getBody();
		try {
			BeanUtils.copyProperties(formdata, itemEnt);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOG.info("Exception occurred while adding item in addItemToWishlist service method");
		}
		LOG.info("Execution ends: addItemToWishlist");
		return formdata;
	}
	@Override
	public ItemForm deleteItemFrmWishlist(String itemId) {
		LOG.info("Execution starts: deleteItemFrmWishlist");
		ItemForm formdata = new ItemForm();
		ResponseEntity<Item> reponseOfDelete = restTemplate
				.postForEntity("http://localhost:9090/wishlist-data-service/wishlist/delete", itemId, Item.class);
		Item itemEnt = reponseOfDelete.getBody();
		try {
			BeanUtils.copyProperties(formdata, itemEnt);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOG.error("Exception occurred while deleting item in deleteItemFrmWishlist service method");
		}
		LOG.info("Execution ends: deleteItemFrmWishlist");
		return formdata;
	}
}
