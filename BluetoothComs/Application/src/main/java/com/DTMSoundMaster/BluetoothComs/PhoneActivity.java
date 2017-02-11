package com.DTMSoundMaster.BluetoothComs;

import android.app.*;
import android.os.*;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothServerSocket;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.lang.reflect.*;

public class PhoneActivity extends Activity {
	
	private static final int REQUEST_ENABLE_BT = 0;
	private static final int REQUEST_DISCOVERABLE_BT = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
		
		final TextView out = (TextView)findViewById(R.id.out);
		final Button b1 = (Button)findViewById(R.id.b1);
		final Button b2 = (Button)findViewById(R.id.b2);
		final Button b3 = (Button)findViewById(R.id.b3);
		final Button b4 = (Button)findViewById(R.id.b4);
		final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		if (mBluetoothAdapter == null) {
			
			out.append("Device Not Supported");
			
		}
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				if (!mBluetoothAdapter.isEnabled()) {
					
					Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
					
				}
				
			}
			
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			
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
		
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				mBluetoothAdapter.disable();
				
				// out.append("Turning Off Bluetooth");
				
				Toast.makeText(getApplicationContext(), "Turning Off Bluetooth", Toast.LENGTH_LONG);
				
			}
			
		});
		
		b4.setOnClickListener(new View.OnClickListener() {
			
				private void showToast(String message) {

					Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

				}
			
			@Override
			public void onClick(View arg0) {
				
				BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
				IntentFilter filter = new IntentFilter();
				
				filter.addAction(BluetoothDevice.ACTION_FOUND);
				filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
				filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
				
				registerReceiver(mReceiver, filter);
				adapter.startDiscovery();
				
			}
			
			private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
				
				public void onReceive(Context context, Intent intent) {
					
					String action = intent.getAction();
					
					if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
						
						// Discovery starts, we can now show progress dialogue or perform other tsaks
						
					} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
						
						// Discovery finishes, dismiss progress dialoogue
						
					} else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
						
						// Bluetooth device found
						
						BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
						showToast("Found Device " + device.getName());
						
					}
					
				}
				
			};
			
			public void onDestroy() {
				
				unregisterReceiver(mReceiver);
				super.onDestroy();
				
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
