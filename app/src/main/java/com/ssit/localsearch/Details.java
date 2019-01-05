package com.ssit.localsearch;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.samsonic.milan.R;
import com.ssit.localsearch.MyLocation.LocationResult;


public class Details extends Activity{

	static int possitionvalue;
	//TextView map, add,latlngdistance;
	
	TextView name,call,heading,add;
	MyLocation myLocation;
	ProgressDialog dlg;
	ImageView back;
	String distance;
	String title;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	      
	        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.details);
	
		Bundle extras;
		extras = getIntent().getExtras();
	    title = extras.getString("name");
	    
//	    title = title.substring(0,1).toUpperCase() + title.substring(1);

	    
		
	    heading=(TextView)findViewById(R.id.detailstitle);
		name = (TextView)findViewById(R.id.detailsname);
		add = (TextView)findViewById(R.id.detailsadd);
	//	latlngdistance = (TextView)findViewById(R.id.detailsdistance);
	//map = (TextView)findViewById(R.id.details_map);
		call = (TextView)findViewById(R.id.detailscall);
		
		int i = title.length();
		if(i>=14){
		title = title.substring(0,14);
		heading.setText(""+title+"..");
				}
		else
		heading.setText(""+title);
		
		new SearchTask(getApplicationContext()).execute();
		
		name.setText(GlobalSearchList.name.get(possitionvalue));
		add.setText(GlobalSearchList.location.get(possitionvalue));
		
		
		
		back =(ImageView)findViewById(R.id.backButton);

		back.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}
		});

		call.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String numberToDial = "tel:" + GlobalSearchList.PhoneNo;
				startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(numberToDial)));

			}
		});

	/*	map.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {
				// TODO Auto-generated method stub

			//	Intent i = new Intent(getApplicationContext(),
			//			MapLocation.class);
			//	i.putExtra("title",title);
			//	startActivity(i);
				;

			}
		});
*/
		myLocation = new MyLocation();
        locationClick();
        
        Constants.dLat = Double.valueOf(GlobalSearchList.lat.get(possitionvalue));
		 Constants.dLng= Double.valueOf(GlobalSearchList.lng.get(possitionvalue));
        
//        String k = getDistance(Constants.cLat,Constants.cLng,Constants.dLat,Constants.dLng);
        
//         double distance; Location curentlocation = new
//				  Location("currentpoint");
//				  curentlocation.setLatitude(Constants.cLat);
//				 curentlocation.setLongitude(Constants.cLng);
//				  
//				 Constants.dLat = Double.valueOf(GlobalSearchList.lat.get(possitionvalue));
//				 Constants.dLng= Double.valueOf(GlobalSearchList.lng.get(possitionvalue));
//				 
//				  Location endlocation = new Location("endpoint");
//				  endlocation.setLatitude(Constants.dLat);
//				  endlocation.setLongitude(Constants.dLng);
//				  
//				  distance = curentlocation.distanceTo(endlocation); 
//				  double k = (distance * 0.621)/1000;
//				  Log.i("distance b/n two locations",""+k);

				
				
				
				
				
				  
}
	
	  private void locationClick() {
	    	
	        myLocation.getLocation(this, locationResult);
	    }

	    public LocationResult locationResult = new LocationResult(){
	       			
			
			public void gotLocation(Location location) {
				// TODO Auto-generated method stub
				  System.out.println("location is"+location);
				 if(location!=null) {
				//    Constants.cLat=location.getLatitude();
				//	Constants.cLng=location.getLongitude();
					
							
				
				 }else {

					 finish();
				 }
			}
	        
	    };
	    public String getDistance(double lat1, double lon1, double lat2, double lon2) {
	    	
	    	    String result_in_kms = "";
	    	
	    	    String url = "http://maps.google.com/maps/api/directions/xml?origin=" + lat1 + "," + lon1 + "&destination=" + lat2 + "," + lon2 + "&sensor=false&units=metric";
	    	
	    	    String tag[] = {"text"};
	    	
	    	    HttpResponse response = null;
	    	
	    	    try {
	    	
	    	        HttpClient httpClient = new DefaultHttpClient();
	    	
	    	        HttpContext localContext = new BasicHttpContext();
	    
	    	        HttpPost httpPost = new HttpPost(url);
	    	
	    	        response = httpClient.execute(httpPost, localContext);
	    
	    	        InputStream is = response.getEntity().getContent();
	    	
	    	        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    	
	    	        Document doc = builder.parse(is);
	    	
	    	        if (doc != null) {
	    	

	    	        	  String nodeValue = null;
	    	        	  NodeList nodeLst1 = doc.getElementsByTagName("DirectionsResponse");
	    	        	
	    	        	  nodeLst1 = doc.getElementsByTagName("distance");
//	    	        	  for (int s = 0; s < nodeLst1.getLength(); s++) {
	    	        	  Node fstNode = nodeLst1.item(nodeLst1.getLength()-1);
	    	        	  Element fstElmnt = (Element) fstNode;
	    	               
	    	        	  
	    	        	  
	                		 NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("text");
	                         Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
	                         NodeList fstNm = fstNmElmnt.getChildNodes();
	                         nodeValue = ((Node) fstNm.item(0)).getNodeValue();
	                         
	                         distance = nodeValue;
	                         
	                         if(distance.equals("")){
	                        	// latlngdistance.setText("Distance not available.");
	                         }
	                         else{
	                   	//  latlngdistance.setText("Distance = "+distance);
	                         Log.i("", "");
	                         }
	    	        	  }
	    	
//	    	        }
	    	
	    	    } catch (Exception e) {
	    	
	    	        e.printStackTrace();
	    		    	    }
	    	
	    	   return result_in_kms;
	    }

	    
	    public class SearchTask extends AsyncTask<Void,Void,Bundle> 
		{ 


		    private Context ctx; 
		    
		    String strRes;
		    
		    public SearchTask(Context context) { 
		        ctx = context; 
		     
		    } 

		     
		    protected void onPreExecute() { 
		        //super.onPreExecute(); 
		   

		       dlg = new ProgressDialog(Details.this); 
		       dlg.setMessage("Loading,Please wait...."); 
		       dlg.setCanceledOnTouchOutside(false);
		       dlg.setCancelable(false);

		       dlg.show();
		        //setContentView(R.layout.splash);
		    } 


		    
		    protected Bundle doInBackground(Void... params) {
		           Bundle b=new Bundle();
		           
//		           key=keyword.getText().toString();
		           
		           
		           
		           GlobalSearchList.getSearchListDetails("https://maps.googleapis.com/maps/api/place/details/xml?sensor=true&key=AIzaSyARSAsQ0iRdO_zxTRII88ZAa5L3DV9g8yU&reference="+GlobalSearchList.referenceKey.get(possitionvalue));
		           
		           
		           
		           return b;
		    }    





		
		protected void onPostExecute(Bundle b) {
			//if (Dashboard.i == 0) {
			//	getDistance(Constants.cLat, Constants.cLng, Constants.dLat,
			//			Constants.dLng);
			//} else {
			//	getDistance(Constants.eLat, Constants.eLng, Constants.dLat,
			//			Constants.dLng);
			//}
			dlg.dismiss();
					
		    
		    }
		    	  
		}
	    
	    protected void onResume() {
	    	
	    	super.onResume();
	    	
	     overridePendingTransition(0,0);
	    	
	    };
	    
}

