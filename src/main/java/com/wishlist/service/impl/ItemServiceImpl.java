package com.wishlist.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.wishlist.WishlistUtility;
import com.wishlist.data.Headphone;
import com.wishlist.data.Mobile;
import com.wishlist.data.Shoe;
import com.wishlist.data.TvBrand;
import com.wishlist.model.Item;
import com.wishlist.repository.ItemRepository;
import com.wishlist.service.ItemService;
import com.wishlist.vo.ItemForm;

@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<ItemForm> fetchWishlistItems() {
		LOG.info("Entered: execution starts in method fetchWishlistItems");
		List<ItemForm> wishlistItem;
		try {
			wishlistItem = WishlistUtility.populateItems(
					StreamSupport.stream(itemRepository.findAll().spliterator(), false).collect(Collectors.toList()));

		} catch (DataAccessException e) {
			LOG.error("Exception occurred while fetching wishlist items");
			throw new ServiceException("Exception occurred while fetching wishlist items");
		}
		LOG.info("Exit: execution ends in method fetchWishlistItems ");
		return wishlistItem;
	}

	@Override
	public ItemForm deleteItemFromWishlist(String itemId) {
		LOG.info("Entered: execution starts in method deleteItemFromWishlist");
		ItemForm itemForm = new ItemForm();
		try {
			Item item = itemRepository.findById(Integer.valueOf(itemId)).orElse(null);
			if (item != null) {
				itemRepository.delete(item);
			}
			BeanUtils.copyProperties(itemForm, item);

		} catch (DataAccessException de) {
			LOG.error("Exception occurred in deleteItemFromWishlist while deleting item to wishlist");
			throw new ServiceException("Exception occurred while deleting item from wishlist");
		} catch (IllegalAccessException | InvocationTargetException ite) {
			LOG.error("Exception occurred in deleteItemFromWishlist while copying porperties from entity to form bean");
			throw new ServiceException(
					"Error occurred in deleteItemFromWishlist while copying porperties from entity to form bean");
		}
		LOG.info("Exit: execution ends in method deleteItemFromWishlist");
		return itemForm;
	}

	@Override
	public ItemForm saveItemToWishlist(ItemForm itemForm) {
		LOG.info("Entered: execution starts in method addItemToWishlist");
		try {
			Item itemEntity = new Item();
			BeanUtils.copyProperties(itemEntity, itemForm);
			if (itemEntity.getItemName().equalsIgnoreCase("Headphone")) {
				itemEntity.setItemDesc(Headphone.getRandomHeadphone().toString());
			} else if (itemEntity.getItemName().equalsIgnoreCase("Shoes")) {
				itemEntity.setItemDesc(Shoe.getRandomShoe().toString());
			} else if (itemEntity.getItemName().equalsIgnoreCase("Television")) {
				itemEntity.setItemDesc(TvBrand.getRandomTv().toString());
			} else if (itemEntity.getItemName().equalsIgnoreCase("Mobile")) {
				itemEntity.setItemDesc(Mobile.getRandomMobile().toString());
			}
			itemRepository.save(itemEntity);
			BeanUtils.copyProperties(itemForm, itemEntity);
		} catch (DataAccessException de) {
			LOG.error("Exception occurred in addItemToWishlist while adding item to wishlist");
			throw new ServiceException("Exception occurred while adding item to wishlist");
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOG.error("Exception occurred in addItemToWishlist while copying properties");
			throw new ServiceException("Exception occurred while copying properties");
		}
		return itemForm;
	}

}
