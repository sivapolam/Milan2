package com.ssit.localsearch;

import com.samsonic.milan.R;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constants {
	protected static String selected_location;
	public static int excellistposition;
	public static double eLat;
	public static double eLng;
	static double dLat;
	static double dLng;
	
	//Change Values 45.465503, 9.185285
	public static final double cLat=45.465503,cLng=9.185285;
	public static final int Att_Count=55;//a
	public static final int Hot_Count=100;//h
	
	public static final String Att_xl="Milan_attractions.xls";
	public static final String Hot_xl="Milan_hotels.xls";
	
	public static final String SnipetName_Att="Milan Attractions";
	public static final String SnipetName_Hot="Milan Hotel";
	
	
	public static final int[] gallery_img={
		R.drawable.b1,
		R.drawable.b2,
		R.drawable.b3,
		R.drawable.b4,
		R.drawable.b5,
		R.drawable.b6,
		R.drawable.b7,
		R.drawable.b8,
		R.drawable.b9,
		R.drawable.b10,
		R.drawable.b11,
		R.drawable.b12,
		R.drawable.b13,
		R.drawable.b14,
		R.drawable.b15,
		R.drawable.b16,
		R.drawable.b17,
		R.drawable.b18,
		R.drawable.b19,
		R.drawable.b20,
		R.drawable.b21,
		R.drawable.b22,
		R.drawable.b23,
		R.drawable.b24,
		R.drawable.b25,
		R.drawable.b26,
		R.drawable.b27
		};

	
	public static boolean ishome;
	public static int nodeLength;
	
	public static boolean isConnected(Context context) {
		   
	     ConnectivityManager cm = (ConnectivityManager)
	     context.getSystemService(Context.CONNECTIVITY_SERVICE);

//	     NetworkInfo wifiNetwork =
//	     cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//	     if (wifiNetwork != null) {
//	     return wifiNetwork.isConnectedOrConnecting();
//	     }
//
//	     NetworkInfo mobileNetwork =
//	     cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//	     if (mobileNetwork != null) {
//	     return mobileNetwork.isConnectedOrConnecting();
//	     }

	     NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
	     if (activeNetwork != null) {
	     return activeNetwork.isConnectedOrConnecting();
	     }

	     return false;
	  }

}
