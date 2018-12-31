package com.wishlist.service;

import java.util.List;

import com.wishlist.vo.ItemForm;


/**
 * @author bsunil
 *
 */
public interface ItemService {
	
	List<ItemForm> fetchWishlistItems() ;
	ItemForm deleteItemFromWishlist(String itemName);
	ItemForm saveItemToWishlist(ItemForm item);
}
