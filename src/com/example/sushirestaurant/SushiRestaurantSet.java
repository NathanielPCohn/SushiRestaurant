package com.example.sushirestaurant;

import java.util.ArrayList;
import java.util.UUID;

public class SushiRestaurantSet {
	private static SushiRestaurantSet sushiSet;
	private ArrayList<SushiRestaurant> sushiRestaurants;
	
	private SushiRestaurantSet() {
		sushiRestaurants = new ArrayList<SushiRestaurant>();
		sushiRestaurants.add(new SushiRestaurant("Shiro's", "Omakase & an ˆ la carte menu prevail in a spartan room at this prominent Belltown fixture.", R.drawable.shiro, 47.614920, -122.346938));
		sushiRestaurants.add(new SushiRestaurant("Mashiko", "Sustainable sushi is on offer at Hajime Sato's hot, artsy spot with a live webcam at the bar.", R.drawable.mashiko, 47.560679, -122.386838));
		sushiRestaurants.add(new SushiRestaurant("Nishino", "Known for its omakase menu, this low-key Japanese choice has inventive sushi in an art-filled space.", R.drawable.nishino, 47.626196, -122.292007));
	}
	
	public static SushiRestaurantSet getInstance() {
		if(sushiSet == null) {
			sushiSet = new SushiRestaurantSet();
		}
		return sushiSet;
	}

	public ArrayList<SushiRestaurant> getSushiRestaurants() {
		return sushiRestaurants;
	}
	
	public SushiRestaurant getSushiRestaurant(UUID id) {
		for (SushiRestaurant item : sushiRestaurants) {
			if (item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}
}
