package com.wishlist.service;

import java.util.List;

import com.wishlist.vo.ItemForm;

public interface WishlistDataService {
	public List<ItemForm> fetchWishlistItems();
	public ItemForm addItemToWishlist(ItemForm itemForm);
	public ItemForm deleteItemFrmWishlist(String itemId);
}
