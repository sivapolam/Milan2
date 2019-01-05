package com.ssit.localsearch;


import com.samsonic.milan.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;



public class Searchlist extends Activity {
	/** Called when the activity is first created. */

	Button attractions, map, clock, search, weather;
	ListView lv;
	ImageView back;
	String title;
	TextView heading;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	      
	        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.searchlist);
		
		Bundle extras;
		extras = getIntent().getExtras();
	    title = extras.getString("keyword");
		
	    heading =(TextView)findViewById(R.id.searchtitletxt);
	    heading.setText(""+title);
		back =(ImageView)findViewById(R.id.backButton);

		back.setOnClickListener(new OnClickListener() {
					
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
					
						finish();
					}
				});
		
		lv = (ListView)findViewById(R.id.listView1);
		
		lv.setAdapter(new Viewadapter(getApplicationContext()));
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Details.possitionvalue=position;
				
				
				
				Intent i = new Intent(getApplicationContext(), Details.class);
				i.putExtra("name", GlobalSearchList.name.get(position));
				startActivity(i);
				
				
				
			}
		});
		
		 
	        
	}
	
	
	
	 
	 public class Viewadapter extends BaseAdapter {
		    private LayoutInflater mInflater;
		    ViewHolder holder;
		    boolean check=false;
		    String resultis;
		    int daysDiff=0;
		    
		  
	    	
		    public Viewadapter(Context context) {
		        // Cache the LayoutInflate to avoid asking for a new one each time.
		        mInflater = LayoutInflater.from(context);
		    }
		    
		    
			   
		    public int getCount() {
		        return GlobalSearchList.name.size();
		        
		    }

		    
		    public Object getItem(int position) {
		        return position;
		    }

		    
		    public long getItemId(int position) {
		        return position;
		    }

		   
		    public View getView(final int position, View convertView, ViewGroup parent) {
		       
			   Drawable drawable=getResources().getDrawable(R.drawable.listback);
		        Drawable[] drawables={drawable,null};
		        
		        if (convertView == null) {
		            convertView = mInflater.inflate(R.layout.joblist, null);

		            holder = new ViewHolder();
		            holder.listlayout=(LinearLayout) convertView.findViewById(R.id.listlayout);
		            holder.title = (TextView) convertView.findViewById(R.id.titletext);
                    holder.condition=(TextView) convertView.findViewById(R.id.condition);
		          
		            holder.status=(TextView) convertView.findViewById(R.id.status);
		            holder.changed=(TextView) convertView.findViewById(R.id.lastchanged);
		            holder.icon=(ImageView) convertView.findViewById(R.id.favicon);
		            holder.today=(TextView) convertView.findViewById(R.id.viewedtoday);
		            
		            convertView.setTag(holder);
		        } else {
		            
		            holder = (ViewHolder) convertView.getTag();
		        }
		        
		        holder.title.setText(GlobalSearchList.name.get(position));
		       
//		        holder.condition.setText(WebSearchList.companyjobemtype.get(position)+", "+WebSearchList.companylocation.get(position));
		        
		        holder.condition.setText(GlobalSearchList.location.get(position));
		        
//		        holder.status.setText(WebSearchList.companydescr.get(position));
		        
		        holder.changed.setText("");
		        
//		        holder.today.setText(valuestoday.get(position));
		        
//		        holder.icon.setImageResource(valuesicon.get(position));
		        
		        if(position%2==1)
		        	holder.listlayout.setBackgroundDrawable(drawable);
		        else
		        	holder.listlayout.setBackgroundColor(Color.WHITE);
//		   
		        	
	        	
		      
		       
		        return convertView;
		    }

		    class ViewHolder {
		    	ImageView icon;
		        TextView title,condition,status,changed;
		        LinearLayout listlayout;
		        TextView today;
		        
		    }
		}
	 
	 
	 protected void onResume() {
	    	
	    	super.onResume();
	    
	     overridePendingTransition(0,0);
	    	
	    };
	 
}

