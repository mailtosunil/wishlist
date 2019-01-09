package com.wishlist;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.wishlist.vo.Item;

public class WishlistUtility {

	private WishlistUtility() {
		super();
	}

	public static List<Item> populateItems(List<Item> items) {
		List<Item> voItems = new ArrayList<>();
		for (Item currentItem : items) {
			Item itm = new Item();
			try {
				BeanUtils.copyProperties(itm, currentItem);
			} catch (IllegalAccessException | InvocationTargetException e) {
			}
			voItems.add(itm);
		}
		return voItems;
	}
}
