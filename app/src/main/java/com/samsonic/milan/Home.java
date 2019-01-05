package com.samsonic.milan;

import java.io.InputStream;   
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.ssit.gallery.GalleryActivity;
import com.ssit.localsearch.Constants;
import com.ssit.localsearch.Dashboard;
import com.ssit.maps.MapsActivity;
import com.ssit.xel.ExcelList;

public class Home extends Activity implements OnClickListener{
	public static int home_position;
	public static String img_path_letter;           
	ImageView IV1,IV2,IV3,IV7,IV8,IV9,IV10;
	static int Textplace;
	
	public static ArrayList<String> excelvalues = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		IV1=(ImageView)findViewById(R.id.overview_iv);
		IV1.setOnClickListener(this);
		IV2=(ImageView)findViewById(R.id.attractions_iv);
		IV2.setOnClickListener(this);
		IV3=(ImageView)findViewById(R.id.hotels_iv);
		IV3.setOnClickListener(this);
		
		IV7=(ImageView)findViewById(R.id.cityfacts_iv);
		IV7.setOnClickListener(this);
		IV8=(ImageView)findViewById(R.id.map_iv);
		IV8.setOnClickListener(this);
		IV9=(ImageView)findViewById(R.id.gallery_iv);
		IV9.setOnClickListener(this);
		IV10=(ImageView)findViewById(R.id.search_iv);
		IV10.setOnClickListener(this);
		
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.overview_iv:
			Intent i_over=new Intent(Home.this,OverView.class);
			startActivity(i_over);
			Textplace=1;
			break;
		case R.id.attractions_iv:
			Constants.ishome=false;
			img_path_letter="a";
			home_position=1;
			getExcelData(this, 1);
			Intent i_att=new Intent(Home.this,ExcelList.class);
			startActivity(i_att);
			break;
		case R.id.hotels_iv:
			Constants.ishome=false;
			img_path_letter="h";
			home_position=2;
			getExcelData(this, 2);
			Intent i_hot=new Intent(Home.this,ExcelList.class);
			startActivity(i_hot);
			break;
		
		case R.id.cityfacts_iv:
			Intent i_cf=new Intent(Home.this,OverView.class);
			startActivity(i_cf);
			Textplace=2;
			break;
		case R.id.map_iv:
			Intent i_map=new Intent(Home.this,MapsActivity.class);
			startActivity(i_map);
			break;
		case R.id.gallery_iv:
			Intent i_gallery=new Intent(Home.this,GalleryActivity.class);
			startActivity(i_gallery);
			//Intent i = new Intent(getApplicationContext(), WorldClock.class);
			//startActivity(i);
			break;
		case R.id.search_iv:
			Constants.ishome=true;
			Intent i_search=new Intent(Home.this,Dashboard.class);
			startActivity(i_search);
			
			break;
		
	
		}
		
	}
	public static void getExcelData(Context context, int a) {
		// TODO Auto-generated method stub

		if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
			Log.w("FileUtils", "Storage not available or read only");
			return;
		}

		try {
			
			excelvalues.clear();

			AssetManager assetManager = context.getAssets();
			String path = null;
			InputStream Stream=null;
			switch (a) {
			case 1:
				 path=Constants.Att_xl;
				break;
			case 2:
				 path=Constants.Hot_xl;
				break;
			
			default:
				break;
			}
			
			Stream = assetManager.open(path);

	    	/*--------- To retrive from SD Card ---------*/
			
//			File root = Environment.getExternalStorageDirectory();
//
//			// File excelFile = new File(root, "filename.xlsx");
//			// Creating Input Stream
//			File file = new File(root, "Uruguay.xls");
//			FileInputStream myInput = new FileInputStream(file);

			/*--------- To retrive from SD Card ---------*/
			
			
			// Create a POIFSFileSystem object
			POIFSFileSystem myFileSystem = new POIFSFileSystem(Stream);

			// Create a workbook using the File System
			HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

			// Get the first sheet from workbook
			HSSFSheet mySheet =myWorkBook.getSheetAt(0);
			//Log.e("rrrrrrrrrrr="+mySheet.getRow(rowIndex), msg)
			/** We now need something to iterate through the cells. **/
			Iterator<Row> rowIter =mySheet.rowIterator();
			
			
			while (rowIter.hasNext()) {
				HSSFRow myRow = (HSSFRow) rowIter.next();
				Iterator<org.apache.poi.ss.usermodel.Cell> cellIter = myRow.cellIterator();
				cellIter.toString();
				StringBuffer text = new StringBuffer();
				//Log.e("textttt=", ""+text);
				while (cellIter.hasNext()) {
					HSSFCell myCell = (HSSFCell) cellIter.next();
					Log.w("FileUtils", "Cell Value: " + myCell.toString());

					text.append("*" + myCell);

				}

				// Adding total row to array
				
				String value = text.toString();
				//Log.e("vvvvvvvvvvvv="+value, "msg");
				if (value.equals("")) {
				} else {
					excelvalues.add(value);
					//Log.e("vvvvvvvvvvvv="+excelvalues, "msg");
				}
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
	}

	public static boolean isExternalStorageReadOnly() {
		String extStorageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
			return true;
		}
		return false;
	}

	public static boolean isExternalStorageAvailable() {
		String extStorageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
			return true;
		}
		return false;
	}
	
	
	
protected void onResume() {
    	
    	super.onResume();
    
     overridePendingTransition(0,0);
    	
    }


}

