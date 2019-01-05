package com.ssit.localsearch;

import java.util.ArrayList;
import java.util.HashMap;

import com.samsonic.milan.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;





public class Dashboard extends Activity implements OnItemClickListener{

ListView LV56;
ProgressDialog dlg;

String keyword,radius,name;
	String[] sname={"Airports",
					"ATMs",
					"Banks",
					"Bars",
					"Beauty Salon",
					"Bus Station",
					"Cafe",
					"Campground",
					"D Store",
					"Doctor",
					"Fire Station",
					"Gas Station",
					"Hospital",
					"Lodging",
					"Mosque",
					"Museum",
					"Park",
					"Parking",
					"Pharmacy",
					"Police",
					"Shopping",
					"Stadium",
					"Taxi Stand",
					"Train Station",
					"Travel Agency",
					"University",
					"Zoo"};
	int[] simg={R.drawable.airport,
				R.drawable.atm,
				R.drawable.bank,
				R.drawable.bar,
				R.drawable.beautysalon,
				R.drawable.busstation,
				R.drawable.cafe,
				R.drawable.campground,
				R.drawable.departmentstore,
				R.drawable.doctor,
				R.drawable.firestation,
				R.drawable.gasstation,
				R.drawable.hospital,
				R.drawable.lodging,
				R.drawable.mosque,
				R.drawable.museum,
				R.drawable.park,
				R.drawable.parking,
				R.drawable.pharmacy,
				R.drawable.police,
				R.drawable.shoppingmall,
				R.drawable.stadium,
				R.drawable.taxistand,   
				R.drawable.trainstation,
				R.drawable.travelagency,
				R.drawable.university,
				R.drawable.zoo};
	
	  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	      
	        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        	setContentView(R.layout.dashboard);
	        	
	        //	Bundle extras;
	    	//	extras = getIntent().getExtras();
	    	 //   cmgfrm = extras.getString("cmgfrom");
	    	    
	    	    ImageView Back=(ImageView)findViewById(R.id.dbbt);
	    	    Back.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						finish();
						
					}
				});
	    	    
	        	LV56=(ListView)findViewById(R.id.lv56);
	        	LV56.setOnItemClickListener(this);
	        	ArrayList<HashMap<String, Object>> AL=new ArrayList<HashMap<String,Object>>();
	       
	        	for(int i=0;i<27;i++){
	        		HashMap<String, Object> hm=new HashMap<String, Object>();
	        		hm.put("IMG",simg[i]);
	        		hm.put("NAME", sname[i]);
	        		AL.add(hm);
	        	}
	        	SimpleAdapter sa=new SimpleAdapter(getApplicationContext(), AL, R.layout.row, new String[]{"IMG","NAME"},new int[]{R.id.iv,R.id.tvv});
	        	LV56.setAdapter(sa);
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int p, long arg3) {
		switch (p) {
		case 0:
			CaseState("Airport", "Airport", "50000");
		
		
			break;
		case 1:
			CaseState("Atm's", "atm", "50000");
			break;
		case 2:
			CaseState("Bank", "bank", "50000");
			break;
		case 3:
			CaseState("Bar", "bar", "50000");
			break;
		case 4:
			CaseState("Beauty Salon", "Saloon", "50000");
			break;
		case 5:
			CaseState("Bus Station", "Busstation", "50000");
			break;
		case 6:
			CaseState("Cafe", "Cafe", "50000");
			break;
		case 7:
			CaseState("Campground", "Campground", "50000");
			break;
		case 8:
			CaseState("Department store", "Departmentstore", "50000");
			break;
		case 9:
			CaseState("Doctor", "doctor", "50000");
			break;
		case 10:
			CaseState("Fire Station", "fire", "50000");
			break;
		case 11:
			CaseState("Gas Station", "Gas", "50000");
			break;
		case 12:
			CaseState("Hospital", "hospital", "50000");
			break;
		case 13:
			CaseState("Lodging", "Lodging", "50000");
			break;
		case 14:
			CaseState("Mosque", "Mosque", "50000");
			break;
		case 15:
			CaseState("Musuem", "Musuem", "50000");
			break;
		case 16:
			CaseState("Park", "park", "50000");
			break;
		case 17:
			CaseState("Parking", "Parking", "50000");
			break;
		case 18:
			CaseState("Pharmacy", "Pharmacy", "50000");
			break;
		case 19:
			CaseState("Police", "police", "50000");
			break;
		case 20:
			CaseState("Shopping", "Mall", "50000");
			break;
		case 21:
			CaseState("Stadium", "Stadium", "50000");
			break;
		case 22:
			CaseState("Taxi Stand", "Taxistand", "50000");
			break;
		case 23:
			CaseState("Train Station", "Train", "50000");
			break;
		case 24:
			CaseState("Travel Agency", "Travelagency", "50000");
			break;
		case 25:
			CaseState("University", "University", "50000");
			break;
		case 26:
			CaseState("Zoo", "Zoo", "50000");
			
			break;

		default:
			break;
		}
	}
	public void CaseState(String n,String k,String r){
		Boolean result = Constants.isConnected(getApplicationContext());
		
		if(result!=false){
			name = n;
		keyword = k;
		  radius = r;
		new SearchTask(getApplicationContext()).execute();

		}
		else{
			
			final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this).create();
			alertDialog.setTitle("Internet");
			alertDialog.setMessage("Network connection not found.");
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			      public void onClick(DialogInterface dialog, int which) {
			 
			       //here you can add functions
			    	  alertDialog.dismiss();
			 
			    } });
			alertDialog.show();
		}
	}
	class SearchTask extends AsyncTask<Void,Void,Bundle> 
	{ 


	    private Context ctx; 
	    
	    String strRes;
	    
	    public SearchTask(Context context) { 
	        ctx = context; 
	     
	    } 

	     
	    protected void onPreExecute() { 
	        //super.onPreExecute(); 
	   

	       dlg = new ProgressDialog(Dashboard.this); 
	       dlg.setMessage("Loading,Please wait....");    
	       dlg.setCanceledOnTouchOutside(false);
	       dlg.setCancelable(false);

	       dlg.show();
	        //setContentView(R.layout.splash);
	    } 


	    
	    protected Bundle doInBackground(Void... params) {
	           Bundle b=new Bundle();
	           
//	           key=keyword.getText().toString();
	          // String url = null;                     
	      
	           String url = "https://maps.googleapis.com/maps/api/place/search/xml?location="+Constants.cLat+","+Constants.cLng+"&radius="+radius+"&keyword="+keyword+"&sensor=true&key=AIzaSyARSAsQ0iRdO_zxTRII88ZAa5L3DV9g8yU";
        	   url = url.replaceAll(" ","%20");
           GlobalSearchList.getSearchList(url);
	         
	           return b;
	    }    





	       
	        protected void onPostExecute(Bundle b) { 

	    	  dlg.dismiss();
	    	  if(Constants.nodeLength!=0)
	    	  {
	    	  Intent i = new Intent(getApplicationContext(), Searchlist.class);
	    	  i.putExtra("keyword", name);
	    	  startActivity(i);
	    	  }
	    	  else{
	    		  
	    		  Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
	    	  }
	    	  
	    		
				
	    
	    }
	        
	
}
}
