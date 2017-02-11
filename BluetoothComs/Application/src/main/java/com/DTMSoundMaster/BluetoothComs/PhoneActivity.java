package com.DTMSoundMaster.BluetoothComs;

import android.app.*;
import android.os.*;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneActivity extends Activity {
	
	private static final int REQUEST_ENABLE_BT = 0;
	private static final int REQUEST_DISCOVERABLE_BT = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
		
		final TextView out = (TextView)findViewById(R.id.out);
		final Button button1 = (Button)findViewById(R.id.button1);
		final Button button2 = (Button)findViewById(R.id.button2);
		final Button button3 = (Button)findViewById(R.id.button3);
		final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		if (mBluetoothAdapter == null) {
			
			out.append("Device Not Supported");
			
		}
		
		button1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				if (!mBluetoothAdapter.isEnabled()) {
					
					Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
					
				}
				
			}
			
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if (!mBluetoothAdapter.isDiscovering()) {
					
					// out.append("Making Your Device Discoverable");
					
					Toast.makeText(getApplicationContext(), "Making Your Device Discoverable", Toast.LENGTH_LONG);
					
					Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
					startActivityForResult(enableBtIntent, REQUEST_DISCOVERABLE_BT);
					
				}
				
			}
			
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View ar0) {
				
				mBluetoothAdapter.disable();
				
				// out.append("Turning Off Bluetooth");
				
				Toast.makeText(getApplicationContext(), "Turning Off Bluetooth", Toast.LENGTH_LONG);
				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.layout.activity_phone, menu);
		
		return true;
		
		}
	
}
