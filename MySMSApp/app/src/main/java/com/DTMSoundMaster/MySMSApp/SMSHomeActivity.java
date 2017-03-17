package com.DTMSoundMaster.MySMSApp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class SMSHomeActivity extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
		
	}
	
	public void newMessage(View v) {
		
		Intent i = new Intent(this, SMSStringView.class);
		startActivity(i);
		
	}
	
}
