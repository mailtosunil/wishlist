package com.wishlist;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wishlist.model.Item;
import com.wishlist.vo.ItemForm;

public class WishlistUtility {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private WishlistUtility() {
		super();
	}

	public static List<ItemForm> populateItems(List<Item> items) {
		LOG.info("Entered: execution starts in method populateItems");
		List<ItemForm> voItems = new ArrayList<>();
		for (Item currentItem : items) {
			ItemForm itm = new ItemForm();
			try {
				BeanUtils.copyProperties(itm, currentItem);
			} catch (IllegalAccessException | InvocationTargetException e) {
				LOG.error("Error occurred in addItemToWishlist while copying properties");
				throw new ServiceException("Exception occurred while copying properties");
			}
			voItems.add(itm);
		}
		LOG.info("Entered: execution ends in method populateItems");
		return voItems;
	}
}
