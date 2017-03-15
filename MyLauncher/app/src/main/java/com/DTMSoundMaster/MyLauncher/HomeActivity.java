package com.DTMSoundMaster.MyLauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends Activity {
	
	static String mtext;
	static String medit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    
	}

    public void showApps(View v) {
        
		Intent i = new Intent(this, AppsListActivity.class);
        startActivity(i);
    
	}
	
	public void enter(View v) {
		
		EditText mEdit;
		mEdit = (EditText)findViewById(R.id.user_input_field);
		medit = mEdit.getText().toString();
		
		TextView mText;
		mText = (TextView)findViewById(R.id.text_field_1);
		
		if (medit.equals("showapps")) {
			
			Intent i = new Intent(this, AppsListActivity.class);
			startActivity(i);
			
			mtext = null;
			mText.setText(mtext);
			
		} else {
			
			mtext = "Not A Valid Input";
			mText.setText(mtext);
			
		}
		
	}
	
}
