package com.DTMSoundMaster.MyLauncher;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;

public class HomeActivity extends Activity {
	
	Button mButton = (Button)findViewById(R.id.enter_input);
	Button aButton = (Button)findViewById(R.id.apps_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
	
	aButton.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {

			Intent i = new Intent(this, AppsListActivity.class);
			startActivity(i);

		}
		
	});
	
	mButton.setOnClickListener(new View.OnClickListener() {
	
		public void enter() {
		
			EditText mEdit = (EditText)findViewById(R.id.user_input);
			mEdit.getText().toString();
		
		}
		
	});

}
