package com.example.sushirestaurant;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MappingFragment extends SupportMapFragment implements ConstantsInterface {
	protected GoogleMap googleMap;
	private SushiRestaurant mSushiRestaurant;
	UUID id;
	
	public static MappingFragment newInstance(UUID mId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_SUSHI_RESTAURANT_ID, mId);
		MappingFragment fragment = new MappingFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(false);
		
		id = null;
		
		if(getArguments() ==  null) {
			id = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_SUSHI_RESTAURANT_ID);
		} else {
			id = (UUID) getArguments().getSerializable(EXTRA_SUSHI_RESTAURANT_ID);
		}
		
		mSushiRestaurant = SushiRestaurantSet.getInstance().getSushiRestaurant(id);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		
		googleMap = getMap();
		googleMap.setMyLocationEnabled(true);
		
		if (id == null) {
			ArrayList<SushiRestaurant> sushiList = SushiRestaurantSet.getInstance().getSushiRestaurants();
			zoomToLocation(sushiList.get(sushiList.size() - 1));
			addMarkers(sushiList);
			
		} else {
			zoomToLocation(mSushiRestaurant);
			addMarker();
		}
		
		return view;
	}
	
	public void zoomToLocation(SushiRestaurant restaurant) {
		LatLng lastPosition = restaurant.getLocation();
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastPosition, 15));
	}
	
	public void addMarker() {
		MarkerOptions marker = new MarkerOptions()
		.position(mSushiRestaurant.getLocation())
		.title(mSushiRestaurant.getTitle())
		.snippet(mSushiRestaurant.getDesc())
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.sushi_icon));
		
		googleMap.addMarker(marker);
	}
	
	public void addMarkers(ArrayList<SushiRestaurant> sushiList) {
		for (SushiRestaurant restaurant: sushiList) {
			MarkerOptions marker = new MarkerOptions()
			.position(restaurant.getLocation())
			.title(restaurant.getTitle())
			.snippet(restaurant.getDesc())
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.sushi_icon));
			
			googleMap.addMarker(marker);
		}
	}
}
