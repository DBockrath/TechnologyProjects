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
	
	private static final String TAG = HomeActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    private Uri uriContact;
    private String contactID;


    
    public void onClickSelectContact(View btnSelectContact) {

        startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
		
    }

    private void retrieveContactPhoto() {

        Bitmap photo = null;

        try {
            
			InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactID)));

            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);
                ImageView imageView = (ImageView) findViewById(R.id.item_app_icon);
                imageView.setImageBitmap(photo);
            }

            assert inputStream != null;
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void retrieveContactNumber() {

        String contactNumber = null;
        Cursor cursorID = getContentResolver().query(uriContact, new String[]{ContactsContract.Contacts._ID}, null, null, null);

        if (cursorID.moveToFirst()) {

            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
			
        }

        cursorID.close();

        Log.d(TAG, "Contact ID: " + contactID);

        Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " + ContactsContract.CommonDataKinds.Phone.TYPE + " = " + ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE, new String[]{contactID}, null);

        if (cursorPhone.moveToFirst()) {
            
			contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			
        }

        cursorPhone.close();

        
    }

    private void retrieveContactName() {

        String contactName = null;

        Cursor cursor = getContentResolver().query(uriContact, null, null, null, null);

        if (cursor.moveToFirst()) {

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }

        cursor.close();

        Log.d(TAG, "Contact Name: " + contactName);

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
			
		}
		
		if (medit.equals("showcontacts")) {
			
			mtext = "Showing All Contacts";
			mText.setText(mtext);
			
			retrieveContactName();
            retrieveContactNumber();
            retrieveContactPhoto();
			
		}
		
	}
		
}
