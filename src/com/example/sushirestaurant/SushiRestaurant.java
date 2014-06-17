package com.example.sushirestaurant;

import java.util.UUID;

import com.google.android.gms.maps.model.LatLng;

public class SushiRestaurant {
	private UUID mId;
	private String mTitle;
	private String mDesc;
	private int mPic;
	private double mLat;
	private double mLng;
	
	public SushiRestaurant(String title, String desc, int pic, double lat, double lng) {
		mId = UUID.randomUUID();
		mTitle = title;
		mDesc = desc;
		mPic = pic;
		mLat = lat;
		mLng = lng;
	}

	public UUID getId() {
		return mId;
	}

	public void setId(UUID id) {
		mId = id;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getDesc() {
		return mDesc;
	}

	public void setDesc(String desc) {
		mDesc = desc;
	}

	public int getPic() {
		return mPic;
	}

	public void setPic(int pic) {
		mPic = pic;
	}
	
	public String toString() {
		return mTitle;
	}
	
	public LatLng getLocation() {
		return new LatLng(mLat, mLng);
	}
}
