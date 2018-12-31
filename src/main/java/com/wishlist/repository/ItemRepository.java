package com.wishlist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wishlist.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer>,CustomItemRepository {
}
