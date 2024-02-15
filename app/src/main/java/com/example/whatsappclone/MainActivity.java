package com.example.whatsappclone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.whatsappclone.ContactActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.whatsappclone.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {



    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        fragmentAdapter fn = new fragmentAdapter(getSupportFragmentManager());
        setSupportActionBar(binding.toolbar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            // Handle search action
            logout();
            return true;
//        } else if (id == R.id.action_settings) {
//            // Handle settings action
//            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, Signup.class);
        startActivity(intent);
        finish();
    }


    public void activityContact(){
        Intent intent = new Intent(this,ContactActivity.class);
        startActivity(intent);

    }
}