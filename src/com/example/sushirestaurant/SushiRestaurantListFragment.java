package com.example.sushirestaurant;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SushiRestaurantListFragment extends ListFragment implements ConstantsInterface {
	private ArrayList<SushiRestaurant> mSushiRestaurants;
	private Callbacks callbacks;
	
	public interface Callbacks {
		void onRestaurantSelected(SushiRestaurant restaurant);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		callbacks = (Callbacks)activity;
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		callbacks = null;
	}
	
	public void updateUI() {
		((SushiRestaurantAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.app_name);
		setHasOptionsMenu(true);
		
		mSushiRestaurants = SushiRestaurantSet.getInstance().getSushiRestaurants();
		
		SushiRestaurantAdapter adapter = new SushiRestaurantAdapter(mSushiRestaurants);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.fragment_sushi_restaurant, 
				container, false);
		return view;
	}

	@Override
	public void onListItemClick(ListView listview, View view, int position, long id) {
		super.onListItemClick(listview, view, position, id);
		
		SushiRestaurant restaurant = (SushiRestaurant)(getListAdapter()).getItem(position);
		callbacks.onRestaurantSelected(restaurant);
	}
	
	private class SushiRestaurantAdapter extends ArrayAdapter<SushiRestaurant> {
		public SushiRestaurantAdapter(ArrayList<SushiRestaurant> sushiList) {
			super(getActivity(), 0, sushiList);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.list_item_restaurant, null);
			}
			
			SushiRestaurant mSushiRestaurant = getItem(position);
			
			TextView titleTextView = (TextView)convertView.findViewById(R.id.list_item_sushi_title);
			titleTextView.setText(mSushiRestaurant.getTitle());
			
			return convertView;
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.to_map, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(getActivity(), MappingActivity.class);
		startActivity(intent);
		return true;
	}
}
