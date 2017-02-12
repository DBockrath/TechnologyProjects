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
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothServerSocket;
import java.util.UUID;
import android.app.ProgressDialog;

public class PhoneActivity extends Activity {
	
	Button b1, b2, b3, b4;
	private BluetoothAdapter BA;
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
		
		UUID appUUID = UUID.fromString("05683628-4936-2146-F8R8JE89V203");
		ProgressDialog mBluetoothConnectProgressDialogue;
		BluetoothSocket mBluetoothSocket;
		BluetoothDevice mBluetoothDevice;
		
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
		
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		
		for(BluetoothDevice bt : BA.getBondedDevices())
		this.items.add(bt.getName());
		this.adapter.notifyDataSetChanged();
		
	}
	
	public void connect(View v) {
		
		String address = BA.getAddress();
		
		
		if (address != "68:c4:4d:a7:2d:9d") {
			
			BA.disable();
			System.out.println("You cannot connect to this device");
			
		} else {
			
		}
		
	}
	
}
