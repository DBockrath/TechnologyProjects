package com.DTMSoundMaster.MyLauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.pm.PackageManager;
import java.util.List;
import java.util.ArrayList;
import android.content.pm.ResolveInfo;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.view.ViewGroup;
import android.util.Log;
import android.net.Uri;
import android.graphics.Bitmap;
import java.io.InputStream;
import android.provider.ContactsContract;
import android.content.ContentUris;
import android.graphics.BitmapFactory;
import java.io.IOException;
import android.database.Cursor;

public class HomeActivity extends Activity {
	
	static String mtext;
	static String medit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    
	}
	
	private PackageManager manager;
	private List<AppDetail> apps; 
	public void loadApps(){

		manager = getPackageManager();
		apps = new ArrayList<AppDetail>();

		Intent i = new Intent(Intent.ACTION_MAIN, null);
		i.addCategory(Intent.CATEGORY_LAUNCHER);

		List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);

		for(ResolveInfo ri:availableActivities){

			AppDetail app = new AppDetail();
			app.label = ri.loadLabel(manager);
			app.name = ri.activityInfo.packageName;
			app.icon = ri.activityInfo.loadIcon(manager);
			apps.add(app);

		}

	}

	private ListView list;    

	public void loadListView(){

		list = (ListView)findViewById(R.id.apps_list);

		ArrayAdapter<AppDetail> adapter = new ArrayAdapter<AppDetail>(this, R.layout.list_item, apps) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				if(convertView == null){
					
					convertView = getLayoutInflater().inflate(R.layout.list_item, null);

				}

				ImageView appIcon = (ImageView)convertView.findViewById(R.id.item_app_icon);
				appIcon.setImageDrawable(apps.get(position).icon);

				TextView appLabel = (TextView)convertView.findViewById(R.id.item_app_label);
				appLabel.setText(apps.get(position).label);

				TextView appName = (TextView)convertView.findViewById(R.id.item_app_name);
				appName.setText(apps.get(position).name);

				return convertView;

			}

		};

		list.setAdapter(adapter);           

	}

	public void addClickListener(){        

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
					Intent i = manager.getLaunchIntentForPackage(apps.get(pos).name.toString());
					HomeActivity.this.startActivity(i);

				}


			});

	}
	
	public void enter(View v) {
		
		EditText mEdit;
		mEdit = (EditText)findViewById(R.id.user_input_field);
		medit = mEdit.getText().toString();
		
		TextView mText;
		mText = (TextView)findViewById(R.id.text_field_1);
		
		if (medit.equals("showapps")) {
			
			mtext = "Showing All Apps";
			mText.setText(mtext);
			
			loadApps();
			loadListView();
			addClickListener();
			
		} else {
			
			
		}
		
	}
		
}
