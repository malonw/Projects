package com.malonwright.DisneyCollection.repositories;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.malonwright.DisneyCollection.models.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	
	List<Item> findAll();
	
	List<Item> findByOrderByNameDesc();
	List<Item> findByOrderByNameAsc();
//	@Query("SELECT distinct item_id FROM likes")
//	List<Item> findAllByOrderByAllLikersDesc();
	List<Item> findByOrderByLikersDesc();
	List<Item> findByOrderByLikersAsc();
	List<Item> findByOrderByRoomItemIsInAsc();
	List<Item> findByOrderByRoomItemIsInDesc();
}
