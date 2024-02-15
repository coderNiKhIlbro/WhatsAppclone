package com.example.whatsappclone;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.service.carrier.CarrierMessagingService;
import android.view.View;

import com.example.whatsappclone.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {


    ActivityProfileBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        registerResult();
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pickImg();
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Profile.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void pickImg() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
    }

    private void registerResult(){

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        try {
                            Uri image = o.getData().getData();
                            binding.profileImage.setImageURI(image);
                    }catch (Exception e){

                        }
                }

                }
        );
    }
}