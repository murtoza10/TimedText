package com.android.sms.timedtext;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PendingIntentsArrayAdapter extends ArrayAdapter<SMSSchedulerPendingIntent> /*implements OnClickListener*/{
	private List<SMSSchedulerPendingIntent> mPendingIntentList;
	private Context mContext;
	
	String newL = System.getProperty("line.separator");
	String messageAlertDialog;
	long mIdOfAnAlarm;
	private CancelAnAlarmActivity mCancelAnAlarmActivity;
	public PendingIntentsArrayAdapter(Context context, int resource,
			 List<SMSSchedulerPendingIntent> objects) {
		super(context, R.layout.cancelanalarm,  objects);
		// TODO Auto-generated constructor stub
		mContext = context;
		mPendingIntentList = objects;
		mCancelAnAlarmActivity = CancelAnAlarmActivity.getCancelAlarmActivity();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	    if (convertView == null) {
	        
	        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.cancelanalarm, parent, false);
	    }
	    TextView to= (TextView)convertView.findViewById(R.id.textViewRecv);
	    TextView tovalue= (TextView)convertView.findViewById(R.id.textViewRecvValue);
	    
	    TextView msg= (TextView)convertView.findViewById(R.id.textViewMsg);
	    TextView msgvalue= (TextView)convertView.findViewById(R.id.textViewMsgValue);
	    
	    TextView hr = (TextView)convertView.findViewById(R.id.textViewHr);
	    TextView hrValue = (TextView)convertView.findViewById(R.id.textViewHrValue);
	    
	    TextView mins = (TextView)convertView.findViewById(R.id.textViewMins);
	    TextView minsValue = (TextView)convertView.findViewById(R.id.textViewMinsValue);
	    
	    TextView secs = (TextView)convertView.findViewById(R.id.textViewSecs);
	    TextView secsValue = (TextView)convertView.findViewById(R.id.textViewSecsValue);
	
	    TextView day = (TextView)convertView.findViewById(R.id.textViewDay);
	    TextView dayValue = (TextView)convertView.findViewById(R.id.textViewDayValue);
	    
	    TextView month = (TextView)convertView.findViewById(R.id.textViewMonth);
	    TextView monthValue = (TextView)convertView.findViewById(R.id.textViewMonthValue);
	    
	    TextView year = (TextView)convertView.findViewById(R.id.textViewYear);
	    TextView yearValue = (TextView)convertView.findViewById(R.id.textViewYearValue);
	    
	    
	    SMSSchedulerPendingIntent smsSchedulerPendingIntent = mPendingIntentList.get(position);
	    String receiverNumber = smsSchedulerPendingIntent.getNumberToSend();
	    String receiverName = smsSchedulerPendingIntent.getReceiverName();
	    String message = smsSchedulerPendingIntent.getMessage();
	    String frequency = smsSchedulerPendingIntent.getFrequency();
	    
	    messageAlertDialog = "Receiver's Number: " + receiverNumber + newL + "Receiver's Name: " + receiverName + newL + "Message: " + message + newL + "Frequency: " + frequency;
	    mIdOfAnAlarm = smsSchedulerPendingIntent.getId();
	    if(position == 0){
	    	to.setText("To");
	    	msg.setText("Message");
	    	hr.setText("Hr");
		    mins.setText("Mins");
		    secs.setText("Secs");
		    day.setText("Day");
		    month.setText("Month");
		    year.setText("Year");
		    
	    }
	    
	    else{
	    	to.setText("");
	    	msg.setText("");
	    	hr.setText("");
		    mins.setText("");
		    secs.setText("");
		    day.setText("");
		    month.setText("");
		    year.setText("");
		    
	    }
	    tovalue.setText(smsSchedulerPendingIntent.getReceiverName());
	    msgvalue.setText(smsSchedulerPendingIntent.getMessage());
	    hrValue.setText(Long.toString(smsSchedulerPendingIntent.getHour()));
	    minsValue.setText(Long.toString(smsSchedulerPendingIntent.getMinutes()));
	    secsValue.setText(Long.toString(smsSchedulerPendingIntent.getSeconds()));
	    
	    dayValue.setText(Integer.toString(smsSchedulerPendingIntent.getDay()));
	    
	    monthValue.setText(Integer.toString(smsSchedulerPendingIntent.getMonth()+1));
	    yearValue.setText(Integer.toString(smsSchedulerPendingIntent.getYear()));
	    
	    
	    return convertView;
	}
	
	public List<SMSSchedulerPendingIntent> getPendingIntentList(){
		return mPendingIntentList;
	}

	

}
