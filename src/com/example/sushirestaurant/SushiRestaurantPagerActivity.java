package com.example.sushirestaurant;

import java.util.ArrayList;
import java.util.UUID;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class SushiRestaurantPagerActivity extends FragmentActivity 
implements ConstantsInterface {
	private ViewPager mViewPager;
	private ArrayList<SushiRestaurant> sushiList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		
		sushiList = SushiRestaurantSet.getInstance().getSushiRestaurants();
		
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

			@Override
			public Fragment getItem(int pos) {
				SushiRestaurant sushiRestaurant = sushiList.get(pos);
				return SushiRestaurantItemFragment.newInstance(sushiRestaurant.getId());
			}

			@Override
			public int getCount() {
				return sushiList.size();
			}
			
		});
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int pos) {
				SushiRestaurant sushiRestaurant = sushiList.get(pos);
				if (sushiRestaurant.getTitle() != null) {
					setTitle(sushiRestaurant.getTitle());
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		UUID mId = (UUID)getIntent()
				.getSerializableExtra(SushiRestaurantItemFragment.EXTRA_SUSHI_RESTAURANT_ID);
		for (int i = 0; i < sushiList.size(); i++) {
			if (sushiList.get(i).getId().equals(mId)) {
				mViewPager.setCurrentItem(i);
				break;
			}
		}
	}
}
