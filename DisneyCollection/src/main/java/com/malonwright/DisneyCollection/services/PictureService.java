package com.malonwright.DisneyCollection.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malonwright.DisneyCollection.models.Item;
import com.malonwright.DisneyCollection.models.Picture;
import com.malonwright.DisneyCollection.repositories.PictureRepository;

@Service
public class PictureService {
	@Autowired
	private PictureRepository pRepo;
	
	//Create Image Object, Save to DB
	public void uploadPic(String url,Item item) {
		Picture newPic = new Picture(url, item);
		this.pRepo.save(newPic);
	}
	
	public List<Picture> itemPictures(Item item){
		return this.pRepo.findAllByItem(item);
	}
	public Picture create(Picture pic) {
		return this.pRepo.save(pic);
	}
}
