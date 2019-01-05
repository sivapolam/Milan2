package com.samsonic.milan;


import com.samsonic.milan.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class OverView extends Activity{

	TextView tcwpb_tv,Text_TV;
	ImageView iv1,iv2;
	
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.overview);
	        iv1=(ImageView)findViewById(R.id.listd_back);
	        
	       
	        iv1.setOnClickListener(new OnClickListener() {
	 			
	 			@Override
	 			public void onClick(View v) {
	 			finish();	
	 			}
	 		});
	
	        tcwpb_tv=(TextView)findViewById(R.id.wpb_guest);
	        Text_TV=(TextView)findViewById(R.id.text_tv);
	       
	        switch (Home.Textplace) {
			case 1:
				
				tcwpb_tv.setText(R.string.overview_st);
				Text_TV.setText("General Info");
				
				break;
			case 2:
				tcwpb_tv.setText(R.string.CityFacts);
				Text_TV.setText("City Facts");
				break;

			default:
				break;
			}
	  
	  }
	 
}
