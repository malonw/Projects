package com.malonwright.DisneyCollection.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malonwright.DisneyCollection.models.Item;
import com.malonwright.DisneyCollection.models.User;
import com.malonwright.DisneyCollection.repositories.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository iRepo;
	
	//Get All Items
	public List<Item> getAllItems(){
		return this.iRepo.findAll();
	}
	
	//Get One Item
	public Item getOneItem(Long id) {
		return this.iRepo.findById(id).orElse(null);
	}
	// Create Item
	public Item createItem(Item item) {
		return this.iRepo.save(item);
	}
	//Edit Item
	public Item editItem(Item item) {
		return this.iRepo.save(item);
	}
	
	//Delete Item
	public void  deleteItem(Long id) {
		this.iRepo.deleteById(id);
	}
	//Like an Item
	public void likeItem(User user, Item item) {
		List<User> usersWhoLiked = item.getLikers();
		usersWhoLiked.add(user);
		this.iRepo.save(item);
	}
	//Unlike an Item
	public void unlikeItem(User user, Item item) {
		List<User> usersWhoLiked = item.getLikers();
		usersWhoLiked.remove(user);
		this.iRepo.save(item);
	}
	//Sort Item Name Up
	public List<Item> sortAsc(Item item) {
		return this.iRepo.findByOrderByNameAsc();
	}
	
	//Sort Item Name Down
	public List<Item> sortDesc(Item item) {
		return this.iRepo.findByOrderByNameDesc();
	}

	//Sort By Likes Up
	public List<Item> sortLikesUp(Item item){
		return this.iRepo.findByOrderByLikersAsc();
	}
	//Sort By Likes Down
	public List<Item> sortLikesDown(Item item){

//		Below Does not work
//		return this.iRepo.findAllByOrderByAllLikersDesc(); 
		return this.iRepo.findByOrderByLikersDesc();
	}
	//Sort By Likes Up
	public List<Item> locationUp(Item item){
		return this.iRepo.findByOrderByRoomItemIsInAsc();
	}
	//Sort By Likes Down
	public List<Item> locationDown(Item item){
		return this.iRepo.findByOrderByRoomItemIsInDesc();
	}

}
