package com.android.sms.timedtext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.Groups;
import android.provider.ContactsContract;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Display2 extends Activity implements OnItemClickListener,OnClickListener{

    List<String> name1 = new ArrayList<String>();
    List<String> phno1 = new ArrayList<String>();
    List<String> grptitle1 = new ArrayList<String>();
    List<String> grpno1 = new ArrayList<String>();
    HashMap<Integer,String> myMapname = new HashMap<Integer,String>();
    HashMap<Integer,String> myMapphno = new HashMap<Integer,String>();
    
    MyAdapter ma1 ;
    Button select;
    int pos;
    String value;
    HashMap<String, Long> group1;
    HashMap<String, Long> group2;
    StringBuilder checkedcontacts1;
    StringBuilder checkedcontacts;
    
    SetuppageActivity mSetuppageActivity;
    EditAnAlarmActivity mEditAnAlarmActivity;
    TabActivity1 mTabActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        getAllContacts(this.getContentResolver());
        ListView lv= (ListView) findViewById(R.id.lv);
            ma1 = new MyAdapter();
            lv.setAdapter(ma1);
            lv.setOnItemClickListener(this); 
            lv.setItemsCanFocus(false);
            lv.setTextFilterEnabled(true);
            // adding
//           select = (Button) findViewById(R.id.button1);
//        select.setOnClickListener(new OnClickListener()
//        {
//
//            public void onClick(View v) {
//                    StringBuilder checkedcontacts= new StringBuilder();
//                    checkedcontacts1= new StringBuilder();
//                    mSetuppageActivity = SetuppageActivity.getMAinActivity();
//                    int f=0;
//                for(int i = 0; i < name1.size(); i++)
//
//                    {
//                    if(ma.mCheckStates.get(i)==true)
//                    {
//                         checkedcontacts.append(name1.get(i).toString());
//                         checkedcontacts.append("\n");
//                         if(f==0){
//                             checkedcontacts1.append(phno1.get(i).toString());
//                        	 //mSetuppageActivity.mEditTextNumber.setText(phno1.get(i).toString());
//                         f++;}
//                         else{
//                        	 //mSetuppageActivity.mEditTextNumber.setText(";"+phno1.get(i).toString());
//                        	 checkedcontacts1.append(";"+phno1.get(i).toString());
//                         }
//
//                    }
//                    else
//                    {
//
//                    }
//
//
//                }
//                mSetuppageActivity.mEditTextNumber.setText(checkedcontacts1);
//                Toast.makeText(Display2.this, checkedcontacts,1000).show();
//                finish();
////                Intent refresh = new Intent();
////                
////			    refresh.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.SetuppageActivity");
////			    
////			    startActivity(refresh);
//            }       
//        });
//
//
    }
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
//         ma.toggle(arg2);
    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Show or Select the Group ...");
		pos=arg2;
		
		alertDialog.setPositiveButton("Show", new DialogInterface.OnClickListener() {
		   public void onClick(DialogInterface dialog, int which) {
		      // TODO Add your code for the button here.
			   Intent i = new Intent(getApplicationContext(), Display3.class);
		    	i.putExtra("new_variable_name",grpno1.get(pos));
		    	startActivity(i);
			   //CancelAnAlarm(mIdOfAnPendingIntent);
		   }
		});
		alertDialog.setNegativeButton("Select", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
		      // TODO Add your code for the button here.
//				Bundle extras = getIntent().getExtras();
//				if (extras != null) {
//				    value = extras.getString("new_variable_name");
//				}
				getSampleContactList(grpno1.get(pos));
				checkedcontacts= new StringBuilder();
                checkedcontacts1= new StringBuilder();
                mSetuppageActivity = SetuppageActivity.getMAinActivity();
                mEditAnAlarmActivity=EditAnAlarmActivity.getMAinActivity();
                int f=0;
            for(int i = 0; i < phno1.size(); i++)
            {
                
                     
                     
                     if(f==0){
                    	 checkedcontacts.append(name1.get(i).toString());
                    	 checkedcontacts1.append(phno1.get(i).toString());
                     }
                     else{
                    	 checkedcontacts.append(","+name1.get(i).toString());
                    	 checkedcontacts1.append(";"+phno1.get(i).toString());
                     }
                     
                     f=1;
                    	 //mSetuppageActivity.mEditTextNumber.setText(phno1.get(i).toString());
                     
                     
                    	 //mSetuppageActivity.mEditTextNumber.setText(";"+phno1.get(i).toString());
                    	 //checkedcontacts1.append(";"+phno1.get(i).toString());


            }

            mTabActivity=TabActivity1.getTabActivity1();
            if (mTabActivity.value1.equals("Setup")){
	    		  mSetuppageActivity.mEditTextNumber.setText(grptitle1.get(pos));
	                mSetuppageActivity.mEditTextNumber10.setText(checkedcontacts1);
	                mSetuppageActivity.mContactdisplayName=checkedcontacts.toString();
	                //mSetuppageActivity.dispnm=1;

	    	  }
	    	 else if (mTabActivity.value1.equals("Edit")){
	    		 
	                mEditAnAlarmActivity.mEditTextNumber.setText(checkedcontacts);
	                mEditAnAlarmActivity.mEditTextNumber11.setText(checkedcontacts1);
	                mEditAnAlarmActivity.mContactdisplayName=checkedcontacts.toString();
	                //mEditAnAlarmActivity.dispnm=1;

	    		}
	    	  
            //Toast.makeText(Display2.this, checkedcontacts,1000).show();
            finish();
		   }
		});
		
		alertDialog.show();
    	
    }

    public  void getAllContacts(ContentResolver cr) {

//        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
//        while (phones.moveToNext())
//        {
//          String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//          String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//          name1.add(name);
//          phno1.add(phoneNumber);
//        }
//        
//        phones.close();
        Cursor groupCursor = cr.query(
                ContactsContract.Groups.CONTENT_URI,
                null, null, null, ContactsContract.Groups.TITLE
        );

        //groupCursor.moveToFirst();
        while (groupCursor.moveToNext()) //
        {
        	String id = groupCursor.getString(groupCursor.getColumnIndex(ContactsContract.Groups._ID));

            String gTitle = (groupCursor.getString(groupCursor.getColumnIndex(ContactsContract.Groups.TITLE)));

        	int i=getSampleContactList1(id);
        	//Toast.makeText(Display2.this, gTitle,1000).show();
        	grpno1.add(id);
        	grptitle1.add(gTitle+"("+i+")");
        	
            //groups.put(tempCur.getString(1), tempCur.getLong(0));
        }
        groupCursor.close();
     }
public void getSampleContactList(String groupID) {

    	
        Uri groupURI = ContactsContract.Data.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.GroupMembership.CONTACT_ID };

        Cursor c = getContentResolver().query(
                groupURI,
                projection,
                ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID
                        + "=" + groupID, null, null);

        while (c.moveToNext()) {
            String id = c
                    .getString(c
                            .getColumnIndex(ContactsContract.CommonDataKinds.GroupMembership.CONTACT_ID));
            Cursor pCur = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[] { id }, null);
            
            while (pCur.moveToNext()) {
                
                String name = pCur
                      .getString(pCur
                              .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

              String phno = pCur
                      .getString(pCur
                              .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

              name1.add(name);
              phno1.add(phno);
                
                
                
            }

            pCur.close();
            

        }
    }


public int getSampleContactList1(String groupID) {

	
    Uri groupURI = ContactsContract.Data.CONTENT_URI;
    String[] projection = new String[] {
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.GroupMembership.CONTACT_ID };

    Cursor c = getContentResolver().query(
            groupURI,
            projection,
            ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID
                    + "=" + groupID, null, null);

    int i=0;
    while (c.moveToNext()) {
        String id = c
                .getString(c
                        .getColumnIndex(ContactsContract.CommonDataKinds.GroupMembership.CONTACT_ID));
        Cursor pCur = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[] { id }, null);
        
        while (pCur.moveToNext()) {
             i++;
            
        }

        pCur.close();
        

    }
    return i;
}

	public void onBackPressed(){
	
//    if(ActiveActivity.active != null){
//   	 
//   	  if (ActiveActivity.active==Display.class){
//   		  Intent refresh1 = new Intent();
//   	    refresh1.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.Display");
//   	    startActivity(refresh1);
//
//   	  }
//   	  else if(ActiveActivity.active==Display1.class){
//   		Intent refresh1 = new Intent();
//   	    refresh1.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.Display1");
//   	    startActivity(refresh1);
//   	  }
//   	  else if(ActiveActivity.active==Display4.class){
//   		Intent refresh1 = new Intent();
//   	    refresh1.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.Display4");
//   	    startActivity(refresh1);
// 	  }
//   	  
//    }
//    Intent refresh1 = new Intent();
//	    refresh1.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.SetuppageActivity");
//	    startActivity(refresh1);
    finish();
	}
//    public HashMap<String, Long> fillGroupsHashMap(HashMap<String, Long> groups) //
//    {
//
//        Cursor tempCur = this.managedQuery(Groups.CONTENT_URI,
//                new String[] { Groups.GROUP_MY_CONTACTS, Groups.NAME }, null, null, null);
//
//        tempCur.moveToFirst();
//        while (tempCur.moveToNext()) //
//        {
//        	name1.add(tempCur.getString(1));
//        	phno1.add(tempCur.getString(0));
//            //groups.put(tempCur.getString(1), tempCur.getLong(0));
//        }
//        return groups;
//    }
    class MyAdapter extends BaseAdapter
    {  
    	//private SparseBooleanArray mCheckStates;
       LayoutInflater mInflater;
        TextView tv;
        //CheckBox cb;
        MyAdapter()
        {
//            mCheckStates = new SparseBooleanArray(name1.size());
            mInflater = (LayoutInflater)Display2.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return grptitle1.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub

            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View vi=convertView;
            if(convertView==null)
             vi = mInflater.inflate(R.layout.row2, null); 
             tv= (TextView) vi.findViewById(R.id.textView5);
             //tv1= (TextView) vi.findViewById(R.id.textView2);
             //cb = (CheckBox) vi.findViewById(R.id.checkBox1);
             tv.setText("GroupName :  "+ grptitle1.get(position));
             //tv1.setText("Phone No :"+ phno1.get(position));
//             cb.setTag(position);
//             cb.setChecked(mCheckStates.get(position, false));
//             cb.setOnCheckedChangeListener(this);

            return vi;
        }
//         public boolean isChecked(int position) {
//                return mCheckStates.get(position, false);
//            }
//
//            public void setChecked(int position, boolean isChecked) {
//                mCheckStates.put(position, isChecked);
//            }
//
//            public void toggle(int position) {
//                setChecked(position, !isChecked(position));
//            }
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView,
//                boolean isChecked) {
//            // TODO Auto-generated method stub
//
//             mCheckStates.put((Integer) buttonView.getTag(), isChecked);         
//        }
		public View getView1(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}   
    }
	public void onItemClick1(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}   
}

