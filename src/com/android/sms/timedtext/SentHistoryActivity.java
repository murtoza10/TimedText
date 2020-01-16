package com.android.sms.timedtext;

import java.util.List;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SentHistoryActivity extends ListActivity implements OnItemClickListener{
	private PendingIntentsDataSource mDatasource;
	private ListView mListView;
	private Button mDetailsButton;
	private Button mCancelButton;
	public static final String tb_name = "pendingintents1";
	private List<SMSSchedulerPendingIntent> mPendingIntentList;
	private String mNumberToSend;
	private String mReceiverName;
	private String mMessage;
	private String mFrequency;
	private int mHour;
	private int mMinutes;
	private int mDay;
	private int mMonth;
	private int mYear;
	private static SentHistoryActivity mSentHistoryActivity;
	private static SetuppageActivity mSetuppageActivity;
	private int mIdOfAnPendingIntent;
	
	
	String newL = System.getProperty("line.separator");
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main1);
	    mSentHistoryActivity = this;
	    mListView = getListView();
	    
	    mDatasource = new PendingIntentsDataSource(this);
	    mDatasource.open();
	    
	    
	    List<SMSSchedulerPendingIntent> values = mDatasource.getAllPendingIntents(tb_name);
	  
	    final PendingIntentsArrayAdapter pendingIntentAdapter = new PendingIntentsArrayAdapter(this, R.layout.history, values);
	    setListAdapter(pendingIntentAdapter);
	    mPendingIntentList = pendingIntentAdapter.getPendingIntentList();
	    
	    mListView.setOnItemClickListener(this);
	  
	}
	
	@Override
    public void onPause(){
    	super.onPause();
    	mDatasource.close();
    	
    }
      @Override
    public void onResume(){
    	super.onResume();
    	ActiveActivity.active = this.getClass();
    	mDatasource.open();
    }
    
    @Override
    public void onStart(){
    	super.onStart();
    	
    	mDatasource.open();
    	
    }
    @Override
    public void onStop(){
    	super.onStop();
    	if(ActiveActivity.active == this.getClass())
    		   ActiveActivity.active = null;
    	mDatasource.close();
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	mDatasource.close();
    }
    
    @Override
    public void onBackPressed(){
    	mDatasource.close();
    	Intent i = new Intent();
    	i.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.OpeningpageActivity");
    	startActivity(i);
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		SMSSchedulerPendingIntent selectedsmsSchedulerPendingIntent = (SMSSchedulerPendingIntent)(getListAdapter().getItem(arg2));
		mIdOfAnPendingIntent = (int) selectedsmsSchedulerPendingIntent.getId();
		mNumberToSend = selectedsmsSchedulerPendingIntent.getNumberToSend();
		
		mMessage = selectedsmsSchedulerPendingIntent.getMessage();
		mHour= selectedsmsSchedulerPendingIntent.getHour();
		mMinutes= selectedsmsSchedulerPendingIntent.getMinutes();
		mDay= selectedsmsSchedulerPendingIntent.getDay();
		mMonth= selectedsmsSchedulerPendingIntent.getMonth();
		mYear= selectedsmsSchedulerPendingIntent.getYear();
		mFrequency = selectedsmsSchedulerPendingIntent.getFrequency();
		String detailsTest = "Receiver's Number: "+ mNumberToSend + newL + "Message: " + mMessage + newL + "Frequency: " + mFrequency;
		ShowHistoryAlarmDetails(detailsTest);
		
	}
	
	public static SentHistoryActivity getSentHistoryActivity(){
		return mSentHistoryActivity;
	}
	@SuppressWarnings("deprecation")
	public void  ShowHistoryAlarmDetails(String message){
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Do you want to cancel this scheduled sms...");
		alertDialog.setMessage(message);
		
		
		alertDialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
		   public void onClick(DialogInterface dialog, int which) {
		      // TODO Add your code for the button here.
			   
			   //CancelAnAlarm(mIdOfAnPendingIntent);
		   }
		});
		alertDialog.setNegativeButton("Yes", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
		      // TODO Add your code for the button here.
				DeleteHistory(mIdOfAnPendingIntent);
		   }
		});
		
		alertDialog.show();
	}
	
	public void DeleteHistory(int id){
		
	    mDatasource.open();
	    mDatasource.deletePendingIntent(tb_name,id);
	    
	    Intent refresh = new Intent();
	    
	    refresh.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.SentHistoryActivity");
	    startActivity(refresh);
	}
	
//	public void DeleteAnForOnceAlarmEntryFromDatabase(int id){
//		mDatasource.deletePendingIntent(id);
//		
//	}
	public int getId(){
		return mIdOfAnPendingIntent;
	}

	public int getHour(){
		return mHour;
	}

	public int getMinutes(){
		return mMinutes;
	}

	public int getYear(){
		return mYear;
	}

	public int getMonth(){
		return mMonth;
	}
	

	public int getDay(){
		return mDay;
	}

	public String getFrequency(){
		return mFrequency;
	}
	
	
	public String getNumberToSend(){
		return mNumberToSend;
	}
	
	
	public String getMessage(){
		return mMessage;
	}
	
	
}