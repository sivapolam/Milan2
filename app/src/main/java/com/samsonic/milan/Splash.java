package com.samsonic.milan;



import com.samsonic.milan.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		SplashHandler mHandler = new SplashHandler();
		setContentView(R.layout.splash);

		Message msg = new Message();

		msg.what = 0;

		mHandler.sendMessageDelayed(msg, 2500);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		overridePendingTransition(0, 0);
	}

	public class SplashHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				super.handleMessage(msg);

				Intent intent = new Intent(Splash.this, Home.class);

				startActivity(intent);
				
				Splash.this.finish();
				break;

			default:
				break;
			}

		}
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		overridePendingTransition(0,0);
	}
}