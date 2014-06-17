package com.example.sushirestaurant;

import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SushiRestaurantItemFragment extends Fragment implements ConstantsInterface {
	private SushiRestaurant mSushiRestaurant;

	public static SushiRestaurantItemFragment newInstance(UUID mId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_SUSHI_RESTAURANT_ID, mId);
		SushiRestaurantItemFragment fragment = new SushiRestaurantItemFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID id = null;
		
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
		View view = inflater.inflate(R.layout.fragment_sushi_restaurant_item, container, false);
		
		initTitle(view);
		initDesc(view);
		initPic(view);
		initMapButton(view);
		
		return view;
	}
	
	public void initTitle(View view) {
		TextView titleTextView = (TextView)view.findViewById(R.id.restaurant_title);
		titleTextView.setText(mSushiRestaurant.getTitle());
	}
	
	public void initDesc(View view) {
		TextView descTextView = (TextView)view.findViewById(R.id.restaurant_desc);
		descTextView.setText(mSushiRestaurant.getDesc());
	}
	
	public void initPic(View view) {
		ImageView picImageView = (ImageView)view.findViewById(R.id.restaurant_pic);
		picImageView.setImageResource(mSushiRestaurant.getPic());
		picImageView.setContentDescription(mSushiRestaurant.getTitle());
	}
	
	public void initMapButton(View view) {
		ImageButton button = (ImageButton)view.findViewById(R.id.map_button);
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), MappingActivity.class);
				intent.putExtra(EXTRA_SUSHI_RESTAURANT_ID, mSushiRestaurant.getId());
				startActivity(intent);
			}
		});
	}
}
