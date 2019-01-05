package com.ssit.gallery;

import java.util.ArrayList;
import java.util.List;

import com.samsonic.milan.R;
import com.ssit.localsearch.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class GalleryActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(this));
        
        gridView.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            {
            	// Sending image id to FullScreenActivity
				Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
				// passing array index
				i.putExtra("id", position);
				startActivity(i);
            }
        });
    }

    private class MyAdapter extends BaseAdapter
    {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public MyAdapter(Context context)
        {
            inflater = LayoutInflater.from(context);

            for(int i=0;i<Constants.gallery_img.length;i++){
            	items.add(new Item(" ", Constants.gallery_img[i]));
            }
            
         /*   items.add(new Item(" ", R.drawable.s2));
            items.add(new Item(" ", R.drawable.s3));
            items.add(new Item(" ", R.drawable.s4));
            items.add(new Item(" ", R.drawable.s5));
            items.add(new Item(" ", R.drawable.s6));
            items.add(new Item(" ", R.drawable.s7));
            items.add(new Item(" ", R.drawable.s8));
            items.add(new Item(" ", R.drawable.s9));
            items.add(new Item(" ", R.drawable.s10));
            items.add(new Item(" ", R.drawable.s11));
            items.add(new Item(" ", R.drawable.s12));
            items.add(new Item(" ", R.drawable.s13));
            items.add(new Item(" ", R.drawable.s14));
            items.add(new Item(" ", R.drawable.s15));
            items.add(new Item(" ", R.drawable.s16));
            items.add(new Item(" ", R.drawable.s17));
            items.add(new Item(" ", R.drawable.s18));
            items.add(new Item(" ", R.drawable.s19));
            items.add(new Item(" ", R.drawable.s20));
            items.add(new Item(" ", R.drawable.s21));
            items.add(new Item(" ", R.drawable.s22));
            items.add(new Item(" ", R.drawable.s23));
            items.add(new Item(" ", R.drawable.s24));
            items.add(new Item(" ", R.drawable.s25));
            items.add(new Item(" ", R.drawable.s26));
            items.add(new Item(" ", R.drawable.s27));
            items.add(new Item(" ", R.drawable.s28));
            items.add(new Item(" ", R.drawable.s29));
            items.add(new Item(" ", R.drawable.s30));
           items.add(new Item(" ", R.drawable.b31));
            items.add(new Item(" ", R.drawable.b32));
            items.add(new Item(" ", R.drawable.b33));
            items.add(new Item(" ", R.drawable.b34));
            items.add(new Item(" ", R.drawable.b35));
            items.add(new Item(" ", R.drawable.b36));
            items.add(new Item(" ", R.drawable.b37));
            items.add(new Item(" ", R.drawable.b38));
            items.add(new Item(" ", R.drawable.b39));
            items.add(new Item(" ", R.drawable.b40));
            */
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i)
        {
            return items.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return items.get(i).drawableId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView picture;
            TextView name;

            if(v == null)
            {
               v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
               v.setTag(R.id.picture, v.findViewById(R.id.picture));
               v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView)v.getTag(R.id.picture);
            name = (TextView)v.getTag(R.id.text);

            Item item = (Item)getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.name);

            return v;
        }

        private class Item
        {
            final String name;
            final int drawableId;

            Item(String name, int drawableId)
            {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }
}