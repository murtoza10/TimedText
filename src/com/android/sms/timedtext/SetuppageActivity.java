package com.android.sms.timedtext;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.sql.DataSource;

import android.R.string;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetuppageActivity extends Activity implements View.OnClickListener{
	
	public String mNumber;
	private String mNumber1;
	EditText mEditTextNumber;
	EditText mEditTextMessage;
	EditText mEditDate;
	EditText mEditTime;
	EditText mEditTextNumber10;
	public String mContactdisplayName;
	private long mInterval;
	private String mMessage;
	public static final String tb_name = "pendingintents";
	private String mFrequency;
	ImageButton mButtonContact;
	Button mDatePickUp;
	Button mTimePickUp;
	Button mConfirm;
	RadioButton mOneTime, mFifteenMinuets, mHalfHour, mHourR, mHalfDay, mDaily, mWeekly, mMonthly, mYearly; 
	private int mHour;
	private int mMinutes;
	private int mSeconds;
	
	private String Date;
	private String Time;
	
	private int mYear;
	private int mMonth;
	private int mDay;
	
    private int mCurrentYear;
    private int mCurrentMonth;
    private int mCurrentDay;
    private int mCurrentHour;
    private int mCurrentMinute;
    
    DatePickerDialog mDatePickerDialog;
    TimePickerDialog mTimePickerDialog;
    Calendar c;
    private static SetuppageActivity mMainActivity;
    private static Display mDisplay;
    private int s=0;
    public int dispnm=0;
    private int mId;
    private boolean isOneTime = false;
    private PendingIntentsDataSource mDatasource;
    private PendingIntentsDataSource1 mDatasource1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setuppage);
        mMainActivity = this;
        mButtonContact = (ImageButton)findViewById(R.id.buttonContact);
        mEditTextNumber = (EditText)findViewById(R.id.editTextNumber);
        mEditTextNumber10 = (EditText)findViewById(R.id.editTextNumber10);
        mEditDate = (EditText)findViewById(R.id.editDate);
        mEditTime = (EditText)findViewById(R.id.editTime);
        mConfirm = (Button)findViewById(R.id.buttonConfirm);
        mEditTextMessage = (EditText)findViewById(R.id.editTextMessage);
        mOneTime = (RadioButton)findViewById(R.id.oneTime);
        mOneTime.setOnClickListener(this);
        mFifteenMinuets = (RadioButton)findViewById(R.id.fifteenMinutes);
        mFifteenMinuets.setOnClickListener(this);
        mHalfHour = (RadioButton)findViewById(R.id.halfHour);
        mHalfHour.setOnClickListener(this);
        mHourR= (RadioButton)findViewById(R.id.hour);
        mHourR.setOnClickListener(this);
        mHalfDay = (RadioButton)findViewById(R.id.halfDay);
        mHalfDay.setOnClickListener(this);
        mDaily = (RadioButton)findViewById(R.id.daily);
        mDaily.setOnClickListener(this);
        mWeekly = (RadioButton)findViewById(R.id.weekly);
        mWeekly.setOnClickListener(this);
        mMonthly = (RadioButton)findViewById(R.id.monthly);
        mMonthly.setOnClickListener(this);
        mYearly = (RadioButton)findViewById(R.id.Yearly);
        mEditTextNumber.setText("");
        mEditTextMessage.setText("");
        
        
        //PendingIntentSQLiteHelper.TABLE_PENDINGINTENT="pendingintents";
        mDatasource = new PendingIntentsDataSource(this);
        mDatasource.open();
//        mDatasource1=new PendingIntentsDataSource1(this);
//        mDatasource1.open();
        c = Calendar.getInstance();
        mCurrentYear = c.get(Calendar.YEAR);
        mCurrentMonth = c.get(Calendar.MONTH);
        mCurrentDay = c.get(Calendar.DAY_OF_MONTH);
        mCurrentHour = c.get(Calendar.HOUR_OF_DAY);
        mCurrentMinute = c.get(Calendar.MINUTE);
        mButtonContact.setOnClickListener( new OnClickListener() {
	    	public void onClick(View v) {
//	    		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//	            intent.setType(ContactsContract.Groups.CONTENT_TYPE);
//	            startActivityForResult(intent, 1);
//	            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI);  
//	            
//	            startActivityForResult(contactPickerIntent, 1001); 
	    		Intent refresh = new Intent();
	    		refresh.putExtra("Activity", "Setup");
	    	    refresh.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.TabActivity1");
	    	    startActivity(refresh);
	    	    //mEditTextNumber.setText(mDisplay.checkedcontacts1);
	    		//startActivityForResult(refresh, 1);
	        }
        });
        mDatePickerDialog = new DatePickerDialog(this, mDateSetListener, mCurrentYear, mCurrentMonth,
				mCurrentDay);
        mTimePickerDialog = new TimePickerDialog(this, mTimeSetListener, mCurrentHour, mCurrentMinute, true);
        mEditDate.setOnTouchListener(new View.OnTouchListener() {
            

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				mDatePickerDialog.show();
				
				return false;
			}
        });
        
        mEditTime.setOnTouchListener(new View.OnTouchListener() {
            
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				mTimePickerDialog.show();
				return false;
			}
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mMessage = mEditTextMessage.getText().toString();
			    mContactdisplayName=mEditTextNumber.getText().toString();
			    
			    String s =mEditTextNumber.getText().toString() ;
			    Pattern p = Pattern.compile("[A-Za-z]");
			    Matcher m = p.matcher(s);
			    if (!m.find()){
			    	mNumber=mEditTextNumber.getText().toString();
			    }
				//mNumber = mEditTextNumber.getText().toString();
//				StringTokenizer st = new StringTokenizer(mNumber,";");
//				int token=st.countTokens();
				
				int f=0;
				if(mMessage.equals("")||mNumber.equals("")||Date.equals("")||Time.equals("")||mFrequency.equals("")){
					Toast.makeText(getApplicationContext(), "Fill out every content of the form", Toast.LENGTH_LONG).show();
					f=1;
				}
				if(f==0){
			    c.set(Calendar.HOUR_OF_DAY, mHour);
			    c.set(Calendar.MINUTE, mMinutes);
			    c.set(Calendar.SECOND, 0);
			    
			    c.set(mYear, mMonth-1, mDay);
			   
//			    if(token!=1){
//					while(st.hasMoreElements()){
//						mNumber1=st.nextToken();
//						Intent i = new Intent(getApplicationContext(), SendSMSAlarmService.class);
//					    i.putExtra("com.android.sms.timedtext.number",mNumber);
//					    i.putExtra("com.android.sms.timedtext.message",mMessage );
//					    i.putExtra("com.android.sms.timedtext.frequency", mFrequency);
//					    
//					    mId = (int) System.currentTimeMillis();
//					    i.putExtra("com.android.sms.timedtext.id", mId);
//					    AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);
//					    PendingIntent pi = PendingIntent.getService(getApplicationContext(), mId, i, PendingIntent.FLAG_UPDATE_CURRENT);
//					    
//					    if(isOneTime == false){
//					    	am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), mInterval, pi);
//					    	addToDatabase(true,mNumber1);			    
//					    	}
//					    if(isOneTime == true){
//					    	am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pi);
//					    	addToDatabase(false,mNumber1);
//					    }	    
//					}
//				}
//			    else{
			    Intent i = new Intent(getApplicationContext(), SendSMSAlarmService.class);
			    i.putExtra("com.android.sms.timedtext.number",mNumber);
			    i.putExtra("com.android.sms.timedtext.message",mMessage );
			    i.putExtra("com.android.sms.timedtext.frequency", mFrequency);
			    i.putExtra("com.android.sms.timedtext.contactname",mContactdisplayName);
			    i.putExtra("com.android.sms.timedtext.hour", mHour);
			    i.putExtra("com.android.sms.timedtext.minutes", mMinutes);
			    i.putExtra("com.android.sms.timedtext.sec", mSeconds);
			    i.putExtra("com.android.sms.timedtext.month", mMonth);
			    i.putExtra("com.android.sms.timedtext.day", mDay);
			    i.putExtra("com.android.sms.timedtext.year", mYear);
			    
			    mId = (int) System.currentTimeMillis();
			    i.putExtra("com.android.sms.timedtext.id", mId);
			    AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);
			    PendingIntent pi = PendingIntent.getService(getApplicationContext(), mId, i, PendingIntent.FLAG_UPDATE_CURRENT);
			    
			    if(isOneTime == false){
			    	am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), mInterval, pi);
			    	addToDatabase(true,mNumber);			    
			    	}
			    if(isOneTime == true){
			    	am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pi);
			    	addToDatabase(false,mNumber);
			    }	    
			    
			    Toast.makeText(getApplicationContext(), "Your SMS has been scheduled...", Toast.LENGTH_LONG).show();
			   
			    Intent refresh = new Intent();
			    refresh.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.SetuppageActivity");
		    	startActivity(refresh);
				}
//			}
			}});
}


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.setuppage, menu);
//        return true;
//    }
//    
//    public boolean onOptionsItemSelected(MenuItem item){
//    	
//        switch(item.getItemId()){
//        case R.id.menuCancelAnAlarm:
//        	Intent i = new Intent();
//        	i.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.CancelAnAlarmActivity");
//        	i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        	startActivity(i);
//        	finish();
//        	return true;
//        }
//        return false;
//    }
    
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
    	 c = Calendar.getInstance();
         mCurrentYear = c.get(Calendar.YEAR);
         mCurrentMonth = c.get(Calendar.MONTH);
         mCurrentDay = c.get(Calendar.DAY_OF_MONTH);
         mCurrentHour = c.get(Calendar.HOUR_OF_DAY);
         mCurrentMinute = c.get(Calendar.MINUTE);
    }
    
    @Override
    public void onStart(){
    	super.onStart();
    	
    	mDatasource.open();
    	 c = Calendar.getInstance();
         mCurrentYear = c.get(Calendar.YEAR);
         mCurrentMonth = c.get(Calendar.MONTH);
         mCurrentDay = c.get(Calendar.DAY_OF_MONTH);
         mCurrentHour = c.get(Calendar.HOUR_OF_DAY);
         mCurrentMinute = c.get(Calendar.MINUTE);
    	
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
    	Intent refresh = new Intent();
	    refresh.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.OpeningpageActivity");
	    startActivity(refresh);
	    
    }
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (data != null) {
	        Uri uri = data.getData();

	        if (uri != null) {
	            Cursor c = null;
	            try {
	                c = getContentResolver().query(ContactsContract.Groups.CONTENT_URI, new String[]{ 
	                		ContactsContract.Groups._ID,
	                        ContactsContract.Groups.TITLE},
	                        null, null, null);
	                
	                if (c != null && c.moveToFirst()) {
	                    
	                    //int type = c.getInt(1);
	                    mEditTextNumber.setText(c.getString(0));
	                    mContactdisplayName = c.getString(1);
	                }
	            } finally {
	                if (c != null) {
	                    c.close();
	                }
	            }
	        }
	    }
    	
    }
    
 
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear+1;
                    mDay = dayOfMonth;
                    
                    if(mDay<10){
                    	Date="0"+mDay;
                    }
                    else{
                    	Date=""+mDay;
                    }
                    if(mMonth<10){
                    	Date+="-0"+mMonth;
                    }
                    else{
                    	Date+="-"+mMonth;
                    }
                    if(mYear<10){
                    	Date+="-0"+mYear;
                    }
                    else{
                    	Date+="-"+mYear;
                    }	
                    mEditDate.setText(Date);
                }
            };

     
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            	mHour = hourOfDay;
                mMinutes = minute;
                
                if(mHour<10){
                	Time="0"+mHour;
                }
                else{
                	Time=""+mHour;
                }
                if(mMinutes<10){
                	Time+=":0"+mMinutes;
                }
                else{
                	Time+=":"+mMinutes;
                }
                	
                mEditTime.setText(Time);
               
                
            }
        };
     
        public static SetuppageActivity getMAinActivity(){
        	return mMainActivity;
        }
        
        public String getNumberToSend(){
        	return mNumber;
        }
        
        public String getSMSMessage(){
        	return mMessage;
        }
        
        public int getDay(){
        	return mDay;
        }
        
        public int getMonth(){
        	return mMonth;
        }
        public int getYear(){
        	return mYear;
        }
        
        public int getHour(){
        	return mHour;
        }
        
        public int getMinutes(){
        	return mMinutes;
        }
        
        public int getSeconds(){
        	return mSeconds;
        }
        
        public PendingIntentsDataSource getDataSource(){
        	
        	return mDatasource;
        }
        

		@Override
		public void onClick(View v) {
			
			// TODO Auto-generated method stub
			
			
			if(v.equals(mOneTime)){
				isOneTime = true;
				mFrequency = "Once";
			}
			if(v.equals(mFifteenMinuets)){
				
				mInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
				mFrequency = "15 mins";
				isOneTime = false;
				
			}
			if(v.equals(mHalfHour)){
				
				mInterval = AlarmManager.INTERVAL_HALF_HOUR;
				mFrequency = "Half Hour";
				
				isOneTime = false;
			}
			if(v.equals(mHourR)){
				
				mInterval = AlarmManager.INTERVAL_HOUR;
				mFrequency = "Hour";
				isOneTime = false;
			}
			if(v.equals(mHalfDay)){
					
					mInterval = AlarmManager.INTERVAL_HALF_DAY;
					mFrequency = "Half Day";
					isOneTime = false;
			}
			if(v.equals(mDaily)){
				
				mInterval = AlarmManager.INTERVAL_DAY;
				mFrequency = "Daily";
				isOneTime = false;
			}
			if(v.equals(mWeekly)){
				
				mInterval = 7*24*60*60*1000;
				mFrequency = "Week";
				isOneTime = false;
				
			}
			if(v.equals(mMonthly)){
				
				isOneTime = false;
				mFrequency = "Month";
				int month = c.get(Calendar.MONTH);
				if((month == Calendar.JANUARY) || (month == Calendar.MARCH) || (month == Calendar.MAY) || (month == Calendar.JULY)|| (month == Calendar.AUGUST) || (month == Calendar.OCTOBER)|| (month == Calendar.DECEMBER)){
					mInterval = 31*24*60*60*1000;
				}
				if((month == Calendar.APRIL) || (month == Calendar.JUNE) || (month == Calendar.SEPTEMBER) || (month == Calendar.NOVEMBER)){
					mInterval = 30*24*60*60*1000;
				}
				if(month == Calendar.FEBRUARY){
					int year = c.get(Calendar.YEAR);
					if ((year%4) == 0){
						mInterval = 29*24*60*60*1000;
					}
					if((year%4) != 0){
						mInterval = 28*24*60*60*1000;
					}
						
				}
				
			}
			if(v.equals(mYearly)){
				
				mInterval = 365*24*60*60*1000;
				mFrequency = "Year";
				isOneTime = false;
			}
			
		}
		
		private void addToDatabase(boolean isRepeated, String mNumber2){
			if(isRepeated == true){
				
				SMSSchedulerPendingIntent newDatabeseEntry = mDatasource.createPendingIntents(tb_name,mId, mHour, mMinutes, mSeconds, mYear, mMonth-1, mDay, mFrequency, mNumber2, mContactdisplayName, mMessage);
			}
			if(isRepeated == false){
				SMSSchedulerPendingIntent newDatabeseEntry = mDatasource.createPendingIntents(tb_name,mId, mHour, mMinutes, mSeconds, mYear, mMonth-1, mDay, mFrequency, mNumber2, mContactdisplayName, mMessage);
			}
		}
		
		
}

