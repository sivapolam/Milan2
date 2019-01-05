package com.ssit.xel;

import java.util.ArrayList;

import com.samsonic.milan.Home;
import com.samsonic.milan.R;
import com.ssit.localsearch.Constants;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ExcelList extends Activity {

	ProgressDialog dlg;
	ListView lv;
	ImageView back;
	ArrayList<Integer> imageid = new ArrayList<Integer>();
	RelativeLayout RLBGL;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.excellist);
		
		RLBGL=(RelativeLayout)findViewById(R.id.rlbgl);
		switch (Home.home_position) {
		case 1:
			RLBGL.setBackgroundResource(R.drawable.bar_attractions);
			break;
		case 2:
			RLBGL.setBackgroundResource(R.drawable.bar_hotels);
			break;
		
		default:
			break;
		}
		
			
		back =(ImageView)findViewById(R.id.backButton);

		back.setOnClickListener(new OnClickListener() {
					
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
					
						finish();
					}
				});

		
		lv = (ListView) findViewById(R.id.excellistview);
		lv.setAdapter(new Viewadapter(getApplicationContext()));
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Constants.excellistposition = position;

				Intent i = new Intent(getApplicationContext(), ExcelList2Details.class);
				startActivity(i);

			}
		});
	}

	public class Viewadapter extends BaseAdapter {
		private LayoutInflater mInflater;
		ViewHolder holder;
		boolean check = false;
		String resultis;
		int daysDiff = 0;

		public Viewadapter(Context context) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
		}

		

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.excellistbg, null);

				holder = new ViewHolder();
				holder.listlayout = (LinearLayout) convertView
						.findViewById(R.id.listlayout);
				holder.title = (TextView) convertView
						.findViewById(R.id.titletext);
								
				holder.locationicon = (ImageView) convertView
						.findViewById(R.id.excelbgimage);
				

				convertView.setTag(holder);
			} else {

				holder = (ViewHolder) convertView.getTag();
			}

		
           
			String name = Home.excelvalues.get(position);
			name = name.substring(1,name.length());
			int index = name.indexOf("*");
			
			name = name.substring(0, index);
			//Log.e("XL NAME="+name, "msg");

			int k = position + 1;
			//image sssssssss
			
			String pathvalue = Home.img_path_letter + k;
			int resID = getResources().getIdentifier(pathvalue, "drawable",getPackageName());			

			   
			

			holder.title.setText(name);

			holder.locationicon.setBackgroundResource(resID);

			return convertView;
		}

		class ViewHolder {
			ImageView icon, locationicon;
			TextView title, condition, status, changed;
			LinearLayout listlayout;
			TextView today;

		}

		@Override
		public int getCount() {
			int pp=0;
			switch (Home.home_position) {
			case 1:
				pp=Constants.Att_Count;
				break;
			case 2:
				pp=Constants.Hot_Count;
				break;
			

			default:
				break;
			}
				
			return pp;
		}
	}
	
	
protected void onResume() {
    	
    	super.onResume();
    
     overridePendingTransition(0,0);
    	
    };
	

}
