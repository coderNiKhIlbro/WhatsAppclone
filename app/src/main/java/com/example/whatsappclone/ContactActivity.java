package com.example.whatsappclone;

import static android.app.PendingIntent.getActivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Person;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.whatsappclone.adapters.ContactsAdapter;
import com.example.whatsappclone.adapters.UserAdapter;
import com.example.whatsappclone.adapters.fragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;
import android.Manifest;
import android.provider.ContactsContract;
public class ContactActivity extends AppCompatActivity {
    String[] usr={"Nikhil","Nisarga","Kundan"};
    ArrayList<String> arrayList= new ArrayList<String>(Arrays.asList(usr));
    ArrayList<Contacts> contacts ;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        contacts = new ArrayList<>();




        recyclerView = findViewById(R.id.recyclerContacts);

        ContactsAdapter adapter = new ContactsAdapter(contacts,ContactActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ContactActivity.this));
        recyclerView.setAdapter(adapter);

        getContacts();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==1){
           if( grantResults[0] == PackageManager.PERMISSION_GRANTED){
               getContacts();
           }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getContacts() {


        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M  && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED )
        {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        );

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


            contacts.add(new Contacts(name,number));
        }
    }
    public class Contacts{
        public String personName;
        public String mobileNumber;
        Contacts(String name,String number){
            this.personName=name;
            this.mobileNumber = number;
        }
    }
}


