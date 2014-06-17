package com.example.sushirestaurant;

import android.support.v4.app.Fragment;

public class MappingActivity extends SingleFragmentActivity 
	implements SushiRestaurantListFragment.Callbacks {
	
	@Override
	protected Fragment createFragment() {
		return new MappingFragment();
	}

	@Override
	public void onRestaurantSelected(SushiRestaurant restaurant) {
		// TODO Auto-generated method stub
		
	}
}
