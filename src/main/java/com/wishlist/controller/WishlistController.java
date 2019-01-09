package com.wishlist.controller;

import java.util.List;

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

	@Autowired
	private WishlistDataService dataService;

	@SuppressWarnings("rawtypes")
	@PostMapping("/addItem")
	@ResponseBody
	public ResponseEntity addItem(@RequestBody String prodId) {
		ResponseEntity responseMessage = null;
		Item item = dataService.addItemToWishlist(prodId);
		responseMessage = new ResponseEntity<Item>(item, HttpStatus.OK);
		return responseMessage;
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/deleteItem/{id}")
	@ResponseBody
	public ResponseEntity deleteItem(@PathVariable String id) {
		ResponseEntity responseMessage = null;
		Item item = dataService.deleteItemFrmWishlist(id);
		responseMessage = new ResponseEntity<Item>(item, HttpStatus.OK);
		return responseMessage;
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/items")
	@ResponseBody
	public ResponseEntity fetchItems() {
		ResponseEntity wishlistRes = null;
		List<Item> wishlistItems = dataService.fetchWishlistItems();
		wishlistRes = new ResponseEntity<>(wishlistItems, HttpStatus.OK);
		return wishlistRes;
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/products")
	@ResponseBody
	public ResponseEntity fetchProducts() {
		ResponseEntity productRes = null;
		List<Product> products = dataService.products();
		productRes = new ResponseEntity<>(products, HttpStatus.OK);
		return productRes;
	}
}
