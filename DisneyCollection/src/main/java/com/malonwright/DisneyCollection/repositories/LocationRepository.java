package com.malonwright.DisneyCollection.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.malonwright.DisneyCollection.models.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {
	List<Location> findAll();
}
