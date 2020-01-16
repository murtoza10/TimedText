package com.android.sms.timedtext;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.Groups;
import android.provider.ContactsContract;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Display extends Activity implements OnItemClickListener,OnClickListener{

    List<String> name1 = new ArrayList<String>();
    List<String> phno1 = new ArrayList<String>();
    List<String> id1 = new ArrayList<String>();
    MyAdapter ma ;
    Button select;
    HashMap<String, Long> group1;
    HashMap<String, Long> group2;
    StringBuilder checkedcontacts1;
    String value="ami";
    SetuppageActivity mSetuppageActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        getAllContacts(this.getContentResolver());
        ListView lv= (ListView) findViewById(R.id.lv);
            ma = new MyAdapter();
            lv.setAdapter(ma);
            lv.setOnItemClickListener(this); 
            //lv.setItemsCanFocus(false);
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
//                Toast.makeText(Display.this, checkedcontacts,1000).show();
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
//        phno1.get(arg2);
    	mSetuppageActivity = SetuppageActivity.getMAinActivity();
        mSetuppageActivity.mEditTextNumber.setText(name1.get(arg2));
        mSetuppageActivity.mNumber=phno1.get(arg2);
        mSetuppageActivity.mContactdisplayName=name1.get(arg2);
        finish();
    	//ma.toggle(arg2);
    }

    
    public  void getAllContacts(ContentResolver cr) {

        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        while (phones.moveToNext())
        {
          String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
          String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
          String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
          name1.add(name);
          phno1.add(phoneNumber);
          id1.add(id);
        }
        
        phones.close();
//        Cursor groupCursor = getContentResolver().query(
//                ContactsContract.Groups.CONTENT_URI,
//                new String[]{
//                        ContactsContract.Groups._ID,
//                        ContactsContract.Groups.TITLE
//                }, null, null, null
//        );
//
//        groupCursor.moveToFirst();
//        while (groupCursor.moveToNext()) //
//        {
//        	name1.add(groupCursor.getString(1));
//        	phno1.add(groupCursor.getString(0));
//            //groups.put(tempCur.getString(1), tempCur.getLong(0));
//        }
//        groupCursor.close();
     }
    
    public static Bitmap loadContactPhoto(ContentResolver cr, long  id) { 
    	Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id); 
    	InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
    	 if (input == null) {  
    	  return null;    
    	 }
    	 return BitmapFactory.decodeStream(input);
    	} 
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.display, menu);
      return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem item){
  	
      switch(item.getItemId()){
      case R.id.mark:
    	  	Intent i = new Intent(Display.this, Display1.class);
	    	
//      	i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      	startActivity(i);
      	finish();
      	return true;
      
      case R.id.markall:
    	  	
        	Intent j = new Intent(Display.this, Display4.class);
	    	
	    	startActivity(j);
//        	j.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.Display1");
////        	i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        	startActivity(j);
        	
        	finish();
        	return true;
      
      case R.id.groups:
        	Intent n = new Intent();
        	n.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.Display2");
//        	n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(n);
        	finish();
        	return true;	
      
      }
      return false;
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
  
  public void onResume() {
		super.onResume();
		ActiveActivity.active = this.getClass();
		System.out.println(ActiveActivity.active);
	}
    class MyAdapter extends BaseAdapter 
    {  //private SparseBooleanArray mCheckStates;
       LayoutInflater mInflater;
        TextView tv1,tv;
        ImageView profile;
        //CheckBox cb;
        MyAdapter()
        {
            //mCheckStates = new SparseBooleanArray(name1.size());
            mInflater = (LayoutInflater)Display.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return name1.size();
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
             vi = mInflater.inflate(R.layout.row, null); 
             tv= (TextView) vi.findViewById(R.id.textView1);
             tv1= (TextView) vi.findViewById(R.id.textView2);
             //cb = (CheckBox) vi.findViewById(R.id.checkBox1);
             tv.setText("Name :  "+ name1.get(position));
             tv1.setText("Phone No :  "+ phno1.get(position));
             ImageView profile = (ImageView) vi.findViewById(R.id.profile_pic);
             if (profile != null) {
            	 long l=Long.parseLong(id1.get(position));
                 Bitmap bitmap = loadContactPhoto(getContentResolver(), l);
      
                 // set it here in the ImageView
                 profile.setImageBitmap(bitmap);
             }
             //cb.setTag(position);
             //cb.setChecked(mCheckStates.get(position, false));
             //cb.setOnCheckedChangeListener(this);

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
