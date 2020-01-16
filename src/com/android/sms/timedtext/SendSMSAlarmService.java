package com.android.sms.timedtext;

import java.util.ArrayList;
import java.util.Currency;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class SendSMSAlarmService extends Service {
 private String mNumberToSend;
 private String mSMSMessage;
 private String mFrequency;
 private String mNumber1;
 private int mId;
 private int mHour;
 private int mMinutes;
 private int mSeconds;
 private int mYear;
 private int mMonth;
 private int mDay;
 public String mContactdisplayName;
 
 private CancelAnAlarmActivity mCancelAnAlarmActivity;
 
 private PendingIntentsDataSource mDatasource,mDatasource2;
 private PendingIntentsDataSource1 mDatasource1;
 
 @Override
 public void onCreate() {
  // TODO Auto-generated method stub
	 super.onCreate();
	 
	 mCancelAnAlarmActivity = CancelAnAlarmActivity.getCancelAlarmActivity();
	 
 }

 @Override
 public IBinder onBind(Intent arg0) {
  // TODO Auto-generated method stub
	 
  return null;
 }
 
 @Override
 public void onDestroy() {
  // TODO Auto-generated method stub
   super.onDestroy();
   
   mDatasource.close();
 }

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
@Override
 public int onStartCommand(Intent intent, int startId, int arg) {
  // TODO Auto-generated method stub
  super.onStartCommand(intent, startId, arg);
  
  	mNumber1 = intent.getStringExtra("com.android.sms.timedtext.number");
  	mContactdisplayName=intent.getStringExtra("com.android.sms.timedtext.contactname");
  	mSMSMessage = intent.getStringExtra("com.android.sms.timedtext.message");
  	mFrequency = intent.getStringExtra("com.android.sms.timedtext.frequency");
  	mId = intent.getIntExtra("com.android.sms.timedtext.id", 0);
  	mHour = intent.getIntExtra("com.android.sms.timedtext.hour", 0);
  	mMinutes=intent.getIntExtra("com.android.sms.timedtext.minutes", 0);
  	mSeconds = intent.getIntExtra("com.android.sms.timedtext.sec", 0);
  	mYear = intent.getIntExtra("com.android.sms.timedtext.year", 0);
  	mMonth = intent.getIntExtra("com.android.sms.timedtext.month", 0);
  	mDay = intent.getIntExtra("com.android.sms.timedtext.day", 0);
  	
  	StringTokenizer st = new StringTokenizer(mNumber1,";");
	int token=st.countTokens();
  	SmsManager sms = SmsManager.getDefault();
  	
		while(st.hasMoreElements()){
			mNumberToSend=st.nextToken();
  	if(!mSMSMessage.equals(null)){
  		
  		ArrayList<String> msgStringArray = sms.divideMessage(mSMSMessage);      

  	    try{sms.sendMultipartTextMessage(mNumberToSend, null, msgStringArray, null, null);
  	  }catch(Exception e){
	    	e.printStackTrace();
	    }
  	}
		}
  	
  	   // mCancelAnAlarmActivity.
//  	    {
  	    	Intent refresh = new Intent();
  	    
     		    refresh.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.CancelAnAlarmActivity");
     		   
     			PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
     			Notification noti = new Notification.Builder(this)
     			.setTicker("Timed Text")
     			.setContentTitle("Timed Text")
     			.setContentText("Scheduled sms have been sent!!")
     			.setSmallIcon(R.drawable.ic_launcher)
     			.setContentIntent(pIntent).getNotification();
     			noti.flags=Notification.FLAG_AUTO_CANCEL;
     			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
     			notificationManager.notify(0, noti);
     			  
     			        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
     			        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
     			        r.play();
     			       if(ActiveActivity.active != null){
     			    	   
     			    	  System.out.println(ActiveActivity.active);
     			    	 
     			    	  if (ActiveActivity.active==CancelAnAlarmActivity.class){
     			    		 System.out.println(ActiveActivity.active);
     			    		  Intent refresh1 = new Intent();
     			    	    
     			    	    refresh1.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.CancelAnAlarmActivity");
     			    	    refresh1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     			    	    startActivity(refresh1);

     			    	  }
     			    	 else if (ActiveActivity.active == SentHistoryActivity.class){
     			    		 Intent refresh1 = new Intent();
     			    	    
     			    	    refresh1.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.SentHistoryActivity");
     			    	    refresh1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     			    	    startActivity(refresh1);

     			    		}
     			    	  }
     		   
  	  
  	
  	
  	if(mFrequency.equalsIgnoreCase("Once")){
//  	PendingIntentSQLiteHelper.TABLE_PENDINGINTENT="pendingintents";
  	mDatasource = new PendingIntentsDataSource(getApplicationContext());
    mDatasource.open();
    mDatasource.deletePendingIntent("pendingintents",mId);
    //mDatasource.close();
//    PendingIntentSQLiteHelper.TABLE_PENDINGINTENT="pendingintents1";
    //mDatasource1 = new PendingIntentsDataSource1(getApplicationContext());
    //mDatasource1.open();
    mDatasource.createPendingIntents("pendingintents1",mId, mHour, mMinutes, mSeconds, mYear, mMonth-1, mDay, mFrequency, mNumber1, mContactdisplayName, mSMSMessage);   
  	}
  	
  	return START_STICKY_COMPATIBILITY;
  
 }

 @Override
 public boolean onUnbind(Intent intent) {
  // TODO Auto-generated method stub
	
  return super.onUnbind(intent);
  
 }

}