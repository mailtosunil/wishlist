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

import com.wishlist.service.ItemService;
import com.wishlist.vo.ItemForm;

/**
 * @author bsunil
 *
 */
@RestController
@RequestMapping("/api")
public class WishlistController {

	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private ItemService itemService;

	@SuppressWarnings("rawtypes")
	@PostMapping("/wishlist/addItem")
	@ResponseBody
	public ResponseEntity addItem(@RequestBody ItemForm item) {
		LOG.info("Entered: execution starts in method addItem");
		LOG.info(String.format("Item received to insert is: %s", item));
		ResponseEntity<ItemForm> responseMessage = null;
		if (item != null) {
			ItemForm itemForm = itemService.saveItemToWishlist(item);
			responseMessage = new ResponseEntity<>(itemForm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Item data cannot be empty", HttpStatus.BAD_REQUEST);
		}
		LOG.info("Exit: execution ends in method addItem");
		return responseMessage;
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/wishlist/deleteItem/{itemId}")
	public ResponseEntity deleteItem(@PathVariable String itemId) {
		LOG.info("Entered: execution starts in method deleteItem");
		LOG.info(String.format("Item received for deletion %s", itemId));
		ResponseEntity<ItemForm> responseMessage = null;
		if (itemId != null && !itemId.isEmpty()) {
			ItemForm itemForm = itemService.deleteItemFromWishlist(itemId);
			responseMessage = new ResponseEntity<>(itemForm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Item Id cannot be empty", HttpStatus.BAD_REQUEST);
		}
		LOG.info("Exit: execution ends in method deleteItem");
		return responseMessage;
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/wishlist/items")
	@ResponseBody
	public ResponseEntity fetchWishlistItems() {
		LOG.info("Entered: execution starts in method fetchWishlistItems");
		ResponseEntity wishlistRes = null;
		List<ItemForm> wishlistItems = itemService.fetchWishlistItems();
		wishlistRes = new ResponseEntity<>(wishlistItems, HttpStatus.OK);
		LOG.info("Exit: execution ends in method fetchWishlistItems");
		return wishlistRes;
	}
}
