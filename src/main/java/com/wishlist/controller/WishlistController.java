package com.wishlist.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wishlist.service.WishlistDataService;
import com.wishlist.vo.Item;
import com.wishlist.vo.Product;

/**
 * @author bsunil
 *
 */
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private WishlistDataService dataService;

	@SuppressWarnings("rawtypes")
	@PostMapping("/addItem")
	@ResponseBody
	public ResponseEntity addItem(@RequestBody String prodId) {
		LOG.info("Entered: execution starts in method addItem");
		LOG.info(String.format("Item received to insert is: %s", prodId));
		ResponseEntity responseMessage = null;
		Item item = dataService.addItemToWishlist(prodId);
		responseMessage = new ResponseEntity<Item>(item, HttpStatus.OK);
		LOG.info("Exit: execution ends in method addItem");
		return responseMessage;
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/deleteItem/{id}")
	@ResponseBody
	public ResponseEntity deleteItem(@PathVariable String id) {
		LOG.info("Entered: execution starts in method deleteItem");
		LOG.info(String.format("Item received for deletion %s", id));
		ResponseEntity responseMessage = null;
		Item item = dataService.deleteItemFrmWishlist(id);
		responseMessage = new ResponseEntity<Item>(item, HttpStatus.OK);
		LOG.info("Exit: execution ends in method deleteItem");
		return responseMessage;
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/items")
	@ResponseBody
	public ResponseEntity fetchItems() {
		LOG.info("Entered: execution starts in method fetchWishlistItems");
		ResponseEntity wishlistRes = null;
		List<Item> wishlistItems = dataService.fetchWishlistItems();
		wishlistRes = new ResponseEntity<>(wishlistItems, HttpStatus.OK);
		LOG.info("Exit: execution ends in method fetchWishlistItems");
		return wishlistRes;
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/products")
	@ResponseBody
	public ResponseEntity fetchProducts() {
		LOG.info("Entered: execution starts in method fetchProducts");
		ResponseEntity productRes = null;
		List<Product> products = dataService.products();
		productRes = new ResponseEntity<>(products, HttpStatus.OK);
		LOG.info("Entered: execution ends in method fetchProducts");
		return productRes;
	}
}
