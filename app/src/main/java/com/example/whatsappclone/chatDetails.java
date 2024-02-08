package com.example.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.whatsappclone.databinding.ActivityChatDetailsBinding;

public class chatDetails extends AppCompatActivity {

    String profileName,mobileNumber;

//    chatDetails(String name,String number){
//        this.profileName =name;
//        this.mobileNumber=number;
//    }


    ActivityChatDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();


    }

    private void loadData() {
        Intent intent = getIntent();
        profileName=intent.getStringExtra("profileName");
        mobileNumber=intent.getStringExtra("number");
        mobileNumber=mobileNumber.replaceAll("\\s", "");;
        if(mobileNumber.length()>10){
            mobileNumber = mobileNumber.substring(mobileNumber.length()-10);
        }

        binding.profileName.setText(profileName);
        binding.number.setText(mobileNumber);
    }
}