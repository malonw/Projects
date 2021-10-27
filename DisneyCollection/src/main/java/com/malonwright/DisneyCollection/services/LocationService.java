package com.malonwright.DisneyCollection.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malonwright.DisneyCollection.models.Item;
import com.malonwright.DisneyCollection.models.Location;
import com.malonwright.DisneyCollection.repositories.LocationRepository;

@Service
public class LocationService {

		@Autowired
		private LocationRepository lRepo;
		
		//Get All Locations
		public List<Location> getAllLocations(){
			return this.lRepo.findAll();
		}
		
		//Get One Location
		public Location getOneLocation(Long id) {
			return this.lRepo.findById(id).orElse(null);
		}
		
		//Create Location
		public Location create(Location location) {
			return this.lRepo.save(location);
		}
}
