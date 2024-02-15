package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappclone.databinding.ActivityOtpVerificationBinding;
import com.example.whatsappclone.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OtpVerification extends AppCompatActivity {


    PhoneAuthCredential credential;
    ActivityOtpVerificationBinding binding;
    String mVerificationId;
    FirebaseAuth mAuth;
    String number;
    String id;
    Boolean success=false;

    String code=null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth= FirebaseAuth.getInstance();


        Intent i =getIntent();
        number=i.getStringExtra("number");
        id = i.getStringExtra("verificationId");

        Toast.makeText(this,number+"in otp",Toast.LENGTH_SHORT).show();

        binding.verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.pinview.getText().toString().length()>6){
                    Toast.makeText(OtpVerification.this,"Enter OTP Firest", Toast.LENGTH_LONG).show();

                }
                else{
                    verify();
                    }

                }
        }
        );
    }


    private void verify() {
        String otp = binding.pinview.getText().toString();
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(id,otp);
        signInWithCredential(phoneAuthCredential);

        Toast.makeText(OtpVerification.this,code+"firebase cha", Toast.LENGTH_LONG).show();


    }


    void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            saveUserData();

                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(OtpVerification.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            binding.pinview.setText("");
                        }
                    }
                });
    }

    private void saveUserData() {
        User myuser= new User("Whatsappp User",number);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        String uid = mAuth.getUid();

        Map<String, Object> user = new HashMap<>();
        user.put("mobile",myuser.getMobile());
        user.put("name",myuser.getName());
        user.put("uid",uid);

        DatabaseReference users=database.child("users").child(uid);

        users.setValue(user)
                .addOnSuccessListener(aVoid -> {
                    // Data added successfully
                    System.out.println("Data added successfully");
                    Toast.makeText(this,"Data added successfully",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(OtpVerification.this, Profile.class);
                    startActivity(i);
                    finish();
                })
                .addOnFailureListener(e -> {
                    // Handle errors
                    System.err.println("Error adding data: " + e.getMessage());
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();

                    success = false;
                });
   }


}