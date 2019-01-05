package com.ssit.maps;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.samsonic.milan.Home;
import com.samsonic.milan.R;
import com.ssit.localsearch.Constants;

public class MapsActivity extends FragmentActivity{

	// Google Map
		private GoogleMap googleMap;
		Double lat =Constants.cLat;
		Double longt =Constants.cLng;
		String Snipet_Name=Constants.SnipetName_Att;
		int Count_XL=1;
		int Count_value=Constants.Att_Count;
		float Marker_color=240.0f;
		String[] XName;
		Double[] XLat,XLng;
		MApTask MT;
		
		Button Attraction_BT,Hotels_BT;
		
		ProgressDialog PD;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.maps_layout);
			
			
			Attraction_BT=(Button)findViewById(R.id.abt);
			Attraction_BT.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Attraction_BT.setBackgroundColor(Color.BLUE);
					Hotels_BT.setBackgroundColor(Color.WHITE);
					
				
					Count_value=Constants.Att_Count;
					Snipet_Name=Constants.SnipetName_Att;
					Count_XL=1;
					Marker_color=240.0f;
					MT=new MApTask();
					MT.execute();
				}
			});
			
			Hotels_BT=(Button)findViewById(R.id.hbt);
			Hotels_BT.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Attraction_BT.setBackgroundColor(Color.WHITE);
					Hotels_BT.setBackgroundColor(Color.BLUE);
					
					Count_value=Constants.Hot_Count;
					Snipet_Name=Constants.SnipetName_Hot;
					Count_XL=2;
					Marker_color=0.0f;
					MT=new MApTask();
					MT.execute();
				}
			});
			

			Attraction_BT.setBackgroundColor(Color.BLUE);
			Hotels_BT.setBackgroundColor(Color.WHITE);
			
			ShowMap();
			

		}
		public void ShowMap(){
			try {
				// Loading map
				Log.e("showmap", "msg");
				initilizeMap();
				MT=new MApTask();
				MT.execute();

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

			
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		public void Loopfor(int length,Double[] lats,Double[] lngs, float f, String[] title_st, String name){
			googleMap.clear();
			for(int i=0;i<length;i++){
				//for(int j=0;j<2;)
				MarkerOptions marker = new MarkerOptions().position(
						new LatLng(lats[i], lngs[i])).title(title_st[i]).snippet(name);//snippet(GetAddress(""+lat_long_arr_attractions[i][0], ""+lat_long_arr_attractions[i][1]));//.title("Hello Maps " + i).snippet(GetAddress(""+ll[i], ""+lo[i]));
						
				marker.icon(BitmapDescriptorFactory
						.defaultMarker(f));
				googleMap.addMarker(marker);
				CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(lat, longt)).zoom(12).build();

				googleMap.animateCamera(CameraUpdateFactory
				.newCameraPosition(cameraPosition));
			}
		}
		public String GetAddress(String lat, String lon)
		{
			Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
			String ret = "";
			try {
				List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lon), 1);
				if(addresses != null) {
					Address returnedAddress = addresses.get(0);
					StringBuilder strReturnedAddress = new StringBuilder();
					for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
						strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
					}
					ret = strReturnedAddress.toString();
				}
				else{
					ret = "No Address returned!";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = "Can't get Address!";
			}
			return ret;
		}
	
		@Override
		protected void onResume() {
			super.onResume();
			//initilizeMap();
		}

		/**
		 * function to load map If map is not created it will create it for you
		 * */
		
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
		public void LoadData(int pos,int count){
			Log.e("pos=="+pos, "msg");
			Log.e("count=="+count, "msg");
			XName=null;
			XLat=null;
			XLng=null;
			 Home.getExcelData(this, pos);
			 XName=new String[count];
			 XLat=new Double[count];
			 XLng=new Double[count];
			 for(int i=0;i<count;i++){
			  String value = Home.excelvalues.get(i);
			  Log.e("no of loops=="+i, "msg");
				value = value.substring(1, value.length());
				int index = value.indexOf("*");
				String mName = value.substring(0, index);
			
				

				value = value.substring(index + 1, value.length());
				index = value.indexOf("*");
				String mAdd = value.substring(0, index);
  
				value = value.substring(index + 1, value.length());
				index = value.indexOf(",");
				//String mLat = value.substring(0, index);
				//Constants.eLat = Double.parseDouble(eLat);
				Double mLat=Double.parseDouble(value.substring(0, index));
				
				value = value.substring(index + 1, value.length());
				index = value.indexOf("*");
				//String mLng = value.substring(0, index);
				//Constants.eLng = Double.parseDouble(eLng);
				Double mLng=Double.parseDouble(value.substring(0, index));
				XName[i]=mName;
				XLat[i]=mLat;
				XLng[i]=mLng;
				
			 }
			
		}

		
		public class MApTask extends AsyncTask<Void,Void,Bundle> 
			{ 

			    protected void onPreExecute() { 
			        //super.onPreExecute(); 
			    	Log.e("onpre", "msg");

			       PD = new ProgressDialog(MapsActivity.this); 
			       PD.setMessage("Loading,Please wait...."); 
			       PD.setCanceledOnTouchOutside(false);
			       PD.setCancelable(false);

			       PD.show();
			     
			    } 


			    
			    protected Bundle doInBackground(Void... params) {
			           Bundle b=new Bundle();
			           Log.e("doin", "msg");
//			        
			         
			           LoadData(Count_XL,Count_value);
			           
			           //GlobalSearchList.getSearchListDetails("https://maps.googleapis.com/maps/api/place/details/xml?sensor=true&key=AIzaSyARSAsQ0iRdO_zxTRII88ZAa5L3DV9g8yU&reference="+GlobalSearchList.referenceKey.get(possitionvalue));
			           
			           
			           
			           return b;
			    }    





			
			protected void onPostExecute(Bundle b) {
				Log.e("onpost", "msg");
				
				Loopfor(Count_value,XLat,XLng,Marker_color, XName,Snipet_Name);
			
				PD.dismiss();
			
			    }
			    	  
			}	
		
		

		
	}
