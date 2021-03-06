package com.android.sms.timedtext;
import android.app.TabActivity;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class TabActivity2 extends TabActivity {
    private static TabActivity2 mTabActivity2;
    public String value1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        mTabActivity2=this;
//        Resources res = getResources();
        Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    value1 = extras.getString("Activity2");
		}
        TabHost mTabHst = getTabHost();
        TabSpec contact = mTabHst.newTabSpec("Contact");
        // setting Title and Icon for the Tab
        contact.setIndicator("Contact", getResources().getDrawable(R.drawable.contact_icon));
        Intent contactIntent = new Intent(this, Display4.class);
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
    public static TabActivity2 getTabActivity2(){
    	return mTabActivity2;
    }
}
