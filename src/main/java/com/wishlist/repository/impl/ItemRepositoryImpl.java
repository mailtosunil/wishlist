package com.wishlist.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wishlist.repository.CustomItemRepository;

@Repository
@Transactional(readOnly = true)
public class ItemRepositoryImpl implements CustomItemRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public int deleteItemByName(String itemName) {
		Query query = entityManager.createNativeQuery("DELETE FROM WISHLIST_ITEMS WHERE ITEM_NAME=?");
		query.setParameter(1, itemName);
		return query.executeUpdate();
	}

}
