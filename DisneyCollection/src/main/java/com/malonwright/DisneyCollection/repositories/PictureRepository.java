package com.malonwright.DisneyCollection.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.malonwright.DisneyCollection.models.Item;
import com.malonwright.DisneyCollection.models.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
	List<Picture> findAllByItem(Item item);
}
