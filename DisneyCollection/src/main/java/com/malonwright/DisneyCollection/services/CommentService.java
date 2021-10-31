package com.malonwright.DisneyCollection.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malonwright.DisneyCollection.models.Comment;
import com.malonwright.DisneyCollection.models.Item;
import com.malonwright.DisneyCollection.repositories.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository cRepo;
	
	public List<Comment> itemComments(Item item){
		return this.cRepo.findAllByItem(item);
	}
	
	public Comment create(Comment comment) {
		return this.cRepo.save(comment);
	}
}
