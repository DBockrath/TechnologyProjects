package com.DTMSoundMaster.MyLauncher;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;

public class HomeActivity extends Activity {
	
	private Intent i = new Intent(this, AppsListActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
		
	public void showApps(View v) {

		startActivity(i);

	}
	
	public void enter() {
		
		EditText mEdit = (EditText)findViewById(R.id.user_input);
		mEdit.getText().toString();
		
	}

}
