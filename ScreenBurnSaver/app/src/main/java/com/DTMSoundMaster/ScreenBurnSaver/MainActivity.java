package com.DTMSoundMaster.ScreenBurnSaver;

import android.app.Activity;
import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import android.os.Handler;

public class MainActivity extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        
		super.onCreate(savedInstanceState);
		
		while (true) {
			
			Handler handler1 = new Handler();
			handler1.postDelayed(new Runnable() {

					@Override
					public void run() {

						setContentView(R.layout.red);

					}

				}, 50);

			Handler handler2 = new Handler();
			handler2.postDelayed(new Runnable() {

					@Override
					public void run() {

						setContentView(R.layout.green);

					}

				}, 150);

			Handler handler3 = new Handler();
			handler3.postDelayed(new Runnable() {

					@Override
					public void run() {

						setContentView(R.layout.blue);

					}

				}, 150);

			Handler handler4 = new Handler();
			handler4.postDelayed(new Runnable() {

					@Override
					public void run() {

						setContentView(R.layout.white);

					}

				}, 200);

			Handler handler5 = new Handler();
			handler5.postDelayed(new Runnable() {

					@Override
					public void run() {

						setContentView(R.layout.black);

					}

				}, 250);
			    try {
	                Thread.sleep(50);
			    } catch (InterruptedException e) {
							
				}
				
		}
		
	}
		
}
