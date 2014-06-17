package com.example.sushirestaurant;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class SushiRestaurantActivity extends SingleFragmentActivity 
	implements SushiRestaurantListFragment.Callbacks, ConstantsInterface {
	
	@Override
	protected Fragment createFragment() {
		return new SushiRestaurantListFragment();
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.activity_master_layout;
	}

	@Override
	public void onRestaurantSelected(SushiRestaurant restaurant) {
		if (findViewById(R.id.item_fragment_container) == null) {
			Intent intent = new Intent(this, SushiRestaurantPagerActivity.class);
			intent.putExtra(EXTRA_SUSHI_RESTAURANT_ID, restaurant.getId());
			startActivity(intent);
		} else {
			FragmentManager manager = getSupportFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			
			Fragment oldItem = manager.findFragmentById(R.id.item_fragment_container);
			Fragment newItem = MappingFragment.newInstance(restaurant.getId());
			
			if (oldItem != null) {
				transaction.remove(oldItem);
			}
			
			transaction.add(R.id.item_fragment_container, newItem);
			transaction.commit();
		}
	}
}
