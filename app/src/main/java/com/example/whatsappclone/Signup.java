package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappclone.databinding.ActivityChatDetailsBinding;
import com.example.whatsappclone.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {


    FirebaseFirestore firestore;
    ActivitySignupBinding binding;

    FirebaseAuth mAuth;
    String number;
    String mVerificationId;
    String mResendToken;

    private static final String PHONE_NUMBER_PATTERN = "^[0-9]{10}$"; // Assumes 10-digit phone numbers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth= FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        if(firebaseUser ==null) {
            binding.next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (validate()) {
//                    Toast.makeText(Signup.this, number, Toast.LENGTH_LONG).show();
                        sendOtp(number);

                    } else {
                        Toast.makeText(Signup.this, "Invalid " + number, Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
        else {
            startActivity(new Intent(Signup.this,MainActivity.class));
            finish();
        }
    }


    private boolean validate() {
        number = binding.phoneNumber.getText().toString().trim();
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);

        // Match the input phone number against the pattern
        Matcher matcher = pattern.matcher(number);

        // Return true if the phone number matches the pattern, false otherwise
        return matcher.matches();
    }



    private void sendOtp(String phoneNumber) {
        phoneNumber="+91" + phoneNumber;
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }


    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks()  {




                @Override
                public void onCodeSent(@NonNull String verificationId,
                                       @NonNull PhoneAuthProvider.ForceResendingToken token) {
                    super.onCodeSent(verificationId, token);

                    // Save verification ID and resending token so we can use them later
                    mVerificationId = verificationId;
//                    String mResendToken = token;

                    Intent intent = new Intent(Signup.this,OtpVerification.class);
                    intent.putExtra("number",number);
                    intent.putExtra("verificationId",verificationId);

                    startActivity(intent);


                    Toast.makeText(getApplicationContext(), verificationId, Toast.LENGTH_LONG).show();
//                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,"000000");
//                    signInWithCredential(phoneAuthCredential);
//
                }
                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    // This callback is invoked in an invalid request for verification is made,
                    // for instance if the the phone number format is not valid.
                    Toast.makeText(getApplicationContext(), "Failed to verify", Toast.LENGTH_LONG).show();
                    // Show a message and update the UI
                }
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                    final String code = credential.getSmsCode();
                    Toast.makeText(getApplicationContext(), code, Toast.LENGTH_SHORT).show();

                }

            };




}
