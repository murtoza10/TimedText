package com.android.sms.timedtext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FrontPageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frontpage);
		Thread pause=new Thread(){
			public void run(){
				try{
					sleep(4000);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					Intent refresh=new Intent();
					refresh.setClassName("com.android.sms.timedtext",
							"com.android.sms.timedtext.OpeningpageActivity");
					startActivity(refresh);
					
				}
			}
		};
		pause.start();
	}
	
	public void onPause() {
		super.onPause();
		finish();
		

	}

	@Override
	public void onResume() {
		super.onResume();
		
	}
	public void onStart() {
		super.onStart();

	}
	public void onDestroy() {
		super.onDestroy();
		finish();

	}

	@Override
	public void onBackPressed() {
		super.onDestroy();
		finish();

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		//intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(intent);

	}
}
