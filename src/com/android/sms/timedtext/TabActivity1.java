package com.android.sms.timedtext;
import android.app.TabActivity;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class TabActivity1 extends TabActivity {
    private static TabActivity1 mTabActivity1;
    public String value1;
    public String value2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        mTabActivity1=this;
//        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    value1 = extras.getString("Activity");
		}
		value2=null;
		 Bundle extras1 = getIntent().getExtras();
 		if (extras1 != null) {
 		    value2 = extras1.getString("Activity1");
 		}
        TabHost mTabHst = getTabHost();
        TabSpec contact = mTabHst.newTabSpec("Contact");
        // setting Title and Icon for the Tab
        contact.setIndicator("Contact", getResources().getDrawable(R.drawable.contact_icon));
        Intent contactIntent = new Intent(this, Display1.class);
        contact.setContent(contactIntent);
         
        // Tab for Songs
        TabSpec group = mTabHst.newTabSpec("Group");       
        group.setIndicator("Group", getResources().getDrawable(R.drawable.group1));
        Intent groupIntent = new Intent(this, Display2.class);
        group.setContent(groupIntent);
        
        mTabHst.addTab(contact); // Adding photos tab
        mTabHst.addTab(group);
     mTabHst.setCurrentTab(0);
    }
    public static TabActivity1 getTabActivity1(){
    	return mTabActivity1;
    }
}
