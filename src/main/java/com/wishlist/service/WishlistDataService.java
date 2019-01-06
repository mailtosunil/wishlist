package com.wishlist.service;

import java.util.List;

import com.wishlist.vo.Item;
import com.wishlist.vo.Product;

public interface WishlistDataService {
	public List<Item> fetchWishlistItems();
	public Item addItemToWishlist(String prodId);
	public Item deleteItemFrmWishlist(String itemId);
	public List<Product> products();
}
