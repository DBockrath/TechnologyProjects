package com.DTMSoundMaster.BluetoothComs;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android. widget.Toast;
import java.util.ArrayList;
import java.util.Set;

public class PhoneActivity extends Activity {
	
	Button b1, b2, b3, b4;
	private BluetoothAdapter BA;
	private Set<BluetoothDevice>pairedDevices;
	private ArrayList<String> items = new ArrayList<>();
	private ArrayAdapter<String> adapter;
	ListView lv1, lv2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone);
		
		b1 = (Button) findViewById(R.id.b1);
		b2 = (Button) findViewById(R.id.b2);
		b3 = (Button) findViewById(R.id.b3);
		b4 = (Button) findViewById(R.id.b4);
		
		BA = BluetoothAdapter.getDefaultAdapter();
		lv1 = (ListView)findViewById(R.id.lv1);
		lv2 = (ListView)findViewById(R.id.lv2);
		
		adapter = new ArrayAdapter<>(this, android.R.layout.activity_list_item, this.items);
		
		lv1.setAdapter(adapter);
	}
	
	public void on(View v) {
		
		if (!BA.isEnabled()) {
			
			Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(turnOn, 0);
			Toast.makeText(getApplicationContext(), "Turned On", Toast.LENGTH_LONG).show();
			
		} else {
			
			Toast.makeText(getApplicationContext(), "Already On", Toast.LENGTH_LONG).show();
			
		}
		
	}
	
	public void off(View v) {
		
		BA.disable();
		Toast.makeText(getApplicationContext(), "Turned Off", Toast.LENGTH_LONG).show();
		
	}
	
	public void visible(View v) {
		
		Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		startActivityForResult(getVisible, 0);
		
	}
	
	public void list(View v) {
		
		pairedDevices = BA.getBondedDevices();
		ArrayList<String> list = new ArrayList<>();
		
		for(BluetoothDevice bt : pairedDevices)
			this.items.add(bt.getName());
		this.adapter.notifyDataSetChanged();
		Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();
		
	}
	
}
