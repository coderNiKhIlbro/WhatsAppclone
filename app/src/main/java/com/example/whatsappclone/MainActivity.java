package com.example.whatsappclone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.example.whatsappclone.adapters.fragmentAdapter;
import com.google.android.material.tabs.TabLayout;

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

        // Watch for button clicks.
//        Button button = (Button)findViewById(R.id.);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pg.setCurrentItem(0);
//            }
//        });





    }
}