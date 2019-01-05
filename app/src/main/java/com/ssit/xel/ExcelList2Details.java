package com.ssit.xel;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.samsonic.milan.Home;
import com.samsonic.milan.R;
import com.ssit.localsearch.Constants;
import com.ssit.localsearch.Dashboard;

  

public class ExcelList2Details extends FragmentActivity{
	String value;
	static String eName,eAdd,eLat,eLng;
	ImageView image;
	TextView name,add;
	LinearLayout lLayout;
	RelativeLayout rLayout;
	Button map,overview,dashbrd;
	//MapView mapView;
	ScrollView sv;
	TextView layoutname;
	ImageView back;
	RelativeLayout layout,RLBGD;

	private GoogleMap googleMap;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	      
	        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.excellistdetails);

		RLBGD=(RelativeLayout)findViewById(R.id.rlbgd);
		
		switch (Home.home_position) {
		case 1:
			RLBGD.setBackgroundResource(R.drawable.bar_attractions);
			break;
		case 2:
			RLBGD.setBackgroundResource(R.drawable.bar_hotels);
			break;
		case 3:
			RLBGD.setBackgroundResource(R.drawable.bar_restaurants);
			break;
		case 4:
			RLBGD.setBackgroundResource(R.drawable.bar_shop);
			break;
		case 5:
			RLBGD.setBackgroundResource(R.drawable.bar_bars);
			break;

		default:
			break;
		}
		
			
		//googleMap = (GoogleMap)findViewById(R.id.map);
  	//	mapView.setBuiltInZoomControls(true);
  	//	mapView.displayZoomControls(true);
		
  		layout = (RelativeLayout)findViewById(R.id.layoutnew);
  		back =(ImageView)findViewById(R.id.backButton);

  		back.setOnClickListener(new OnClickListener() {
  					
  					
  					public void onClick(View v) {
  						// TODO Auto-generated method stub
  					
  						finish();
  					}
  				});
  		
  		
		value = Home.excelvalues.get(Constants.excellistposition);
		
		value = value.substring(1, value.length());
		int index = value.indexOf("*");
		eName = value.substring(0, index);

		value = value.substring(index + 1, value.length());
		index = value.indexOf("*");
		eAdd = value.substring(0, index);

		value = value.substring(index + 1, value.length());
		index = value.indexOf(",");
		eLat = value.substring(0, index);
		Constants.eLat = Double.parseDouble(eLat);
		
		value = value.substring(index + 1, value.length());
		index = value.indexOf("*");
		eLng = value.substring(0, index);
		Constants.eLng = Double.parseDouble(eLng);
		
		name = (TextView) findViewById(R.id.exceldtlsname);
		add = (TextView) findViewById(R.id.exceldtlsdesc);
		lLayout =(LinearLayout)findViewById(R.id.llayout);
		rLayout =(RelativeLayout)findViewById(R.id.rlayout);
		map = (Button)findViewById(R.id.exceldetailsmap);
		overview =(Button)findViewById(R.id.exceldetailsoverview);
		dashbrd =(Button)findViewById(R.id.exceldetailssearch);
		layoutname =(TextView)findViewById(R.id.layoutname);
		sv =(ScrollView)findViewById(R.id.scrollView1);
				
		
		// To get the image id's from drawable folder
		int k = Constants.excellistposition + 1;
		String pathvalue = Home.img_path_letter + k;
		int imageResID = getResources().getIdentifier(pathvalue, "drawable",
				getPackageName());

		// To get the image id's from drawable folder	
		
		
		image = (ImageView) findViewById(R.id.excellistdetimage);
//		image.setImageResource(Constants.images[Constants.excellistposition]);
		image.setImageResource(imageResID);

		
		if (eName.length()>=25) {
			
			eName = eName.substring(0,25);
			name.setText(eName+"..");
			
		} else {

			name.setText(eName);
		}
		
		add.setText(eAdd);
		
		dashbrd.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//    Constants.i=false;
				Intent i = new Intent(getApplicationContext(), Dashboard.class);
				i.putExtra("cmgfrom","excellist");
				
				startActivity(i);
				
			}
		});
		
		
		overview.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    layoutname.setText("OverView");
				lLayout.setVisibility(View.VISIBLE);
				rLayout.setVisibility(View.GONE);
				
				sv.setVisibility(View.VISIBLE);
				map.setBackgroundDrawable(getResources().getDrawable(R.drawable.mapbutton));
				overview.setBackgroundDrawable(getResources().getDrawable(R.drawable.infobtnselect));
				
				
				
			}
		});
		
		
		map.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				layoutname.setText("Map");
				lLayout.setVisibility(View.GONE);
				rLayout.setVisibility(View.VISIBLE);
				sv.setVisibility(View.GONE);
				myMapss();
				
			//	map.setBackgroundDrawable(getResources().getDrawable(R.drawable.mapbtnselext));
			//	overview.setBackgroundDrawable(getResources().getDrawable(R.drawable.infobtn));
//				
//				 LinearLayout.LayoutParams paddingLp=new LinearLayout.LayoutParams(MarginLayoutParams.WRAP_CONTENT,MarginLayoutParams.WRAP_CONTENT);
//			      	
//		      	 paddingLp.leftMargin=10;
//		      	layout.setLayoutParams(paddingLp);
			//	List<Overlay> mapOverlays = mapView.getOverlays();

		  	//	Drawable drawable = ExcelList2Details.this.getResources().getDrawable(R.drawable.redmarker);
		  	//	CustomItemizedOverlayNew itemizedOverlay =

		  		//new CustomItemizedOverlayNew(drawable, ExcelList2Details.this);
		  	//		double lat = Double.parseDouble(eLat);
		  	//		double lan = Double.parseDouble(eLng);


	//	  			GeoPoint point = new GeoPoint((int) (lat * 1E6),(int) (lan * 1E6));
//
		//  			OverlayItem overlayitem = new OverlayItem(point,"Name : ",eName);
//
		//  			itemizedOverlay.addOverlay(overlayitem);
		//  			mapOverlays.add(itemizedOverlay);
		 // 			MapController mapController = mapView.getController();
		//  			mapController.animateTo(point);
		//  			mapController.setZoom(13);
							
			}
		});
		
		
}

	
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	protected void myMapss() {
		try {
			// Loading map
			initilizeMap();

			// Changing map type
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

			// Showing / hiding your current location
			googleMap.setMyLocationEnabled(true);

			// Enable / Disable zooming controls
			googleMap.getUiSettings().setZoomControlsEnabled(false);

			// Enable / Disable my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			// Enable / Disable Compass icon
			googleMap.getUiSettings().setCompassEnabled(true);

			// Enable / Disable Rotate gesture
			googleMap.getUiSettings().setRotateGesturesEnabled(true);

			// Enable / Disable zooming functionality
			googleMap.getUiSettings().setZoomGesturesEnabled(true);

			

			// lets place some 10 random markers
			

				// Adding a marker
				MarkerOptions marker = new MarkerOptions().position(
						new LatLng(Constants.eLat, Constants.eLng));
						
				marker.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
				
/*
				// changing marker color
				if (i == 0)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
				if (i == 1)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
				if (i == 2)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
				if (i == 3)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				if (i == 4)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
				if (i == 5)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
				if (i == 6)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				if (i == 7)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
				if (i == 8)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
				if (i == 9)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
*/
				googleMap.addMarker(marker);

				// Move the camera to last position with a zoom level
				
					CameraPosition cameraPosition = new CameraPosition.Builder()
							.target(new LatLng(Constants.eLat, Constants.eLng)).zoom(15).build();

					googleMap.animateCamera(CameraUpdateFactory
							.newCameraPosition(cameraPosition));
				
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void initilizeMap() {
		if (googleMap == null) {
		//	googleMap = ((SupportFragmentManager) getSupportFragmentManager().findFragmentById(
		//	R.id.map)).getMap();
			android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
		    SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager
		            .findFragmentById(R.id.map);
		    googleMap = mapFragment.getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

protected void onResume() {
    	
    	super.onResume();
    
//     overridePendingTransition(0,0);
    	
    };
	
}