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
import android.content.pm.PackageManager;
import java.util.List;
import java.util.ArrayList;
import android.content.pm.ResolveInfo;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SMSHomeActivity extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
		
		loadDetails();
		loadListView();
		
	}
	
	private PackageManager manager;
	private List<MessageDetail> details;
	public void loadDetails(){

		manager = getPackageManager();
		details = new ArrayList<MessageDetail>();

		Intent i = new Intent(Intent.ACTION_MAIN, null);
		i.addCategory(Intent.CATEGORY_LAUNCHER);

		List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);

		for(ResolveInfo ri:availableActivities){

			MessageDetail detail = new MessageDetail();
			detail.label = ri.loadLabel(manager);
			detail.name = ri.activityInfo.packageName;
			detail.icon = ri.activityInfo.loadIcon(manager);
			details.add(detail);

		}

	}

	private ListView list;    

	public void loadListView(){

		list = (ListView)findViewById(R.id.message_field);

		ArrayAdapter<MessageDetail> adapter = new ArrayAdapter<MessageDetail>(this, R.layout.list_item, details) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				if(convertView == null){

					convertView = getLayoutInflater().inflate(R.layout.list_item, null);

				}

				ImageView appIcon = (ImageView)convertView.findViewById(R.id.item_recipient_icon);
				appIcon.setImageDrawable(details.get(position).icon);

				TextView appLabel = (TextView)convertView.findViewById(R.id.item_text_message);
				appLabel.setText(details.get(position).label);

				TextView appName = (TextView)convertView.findViewById(R.id.item_recipient_name);
				appName.setText(details.get(position).name);

				return convertView;

			}

		};

		list.setAdapter(adapter);           

	}
	
	public void newMessage(View v) {
		
		Intent intent = new Intent(SMSHomeActivity.this, SMSStringView.class);
		startActivity(intent);
		
	}
	
}
