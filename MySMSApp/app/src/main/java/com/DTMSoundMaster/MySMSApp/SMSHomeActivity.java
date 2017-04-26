package com.DTMSoundMaster.MySMSApp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.content.ContentResolver;
import android.database.Cursor;
import java.util.Date;
import android.net.Uri;
import java.text.SimpleDateFormat;
import android.widget.ArrayAdapter;

public class SMSHomeActivity extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
		
	}
	
	public void newMessage(View v) {
		
		Intent intent = new Intent(SMSHomeActivity.this, SMSStringView.class);
		startActivity(intent);
		
	}
	
}
