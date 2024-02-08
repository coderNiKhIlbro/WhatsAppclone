package com.example.whatsappclone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.whatsappclone.ContactActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.whatsappclone.adapters.UserAdapter;
import com.example.whatsappclone.adapters.fragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentAdapter fn = new fragmentAdapter(getSupportFragmentManager());


        ViewPager pg = (ViewPager)findViewById(R.id.pager);
        pg.setAdapter(fn);
        TabLayout tb = (TabLayout) findViewById(R.id.tb);
        tb.setupWithViewPager(pg);

        ImageButton button = findViewById(R.id.contact);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activityContact();
            }
        });


    }
    public void activityContact(){
        Intent intent = new Intent(this,ContactActivity.class);
        startActivity(intent);

    }
}