package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappclone.adapters.ChattingAdapter;
import com.example.whatsappclone.databinding.ActivityChatDetailsBinding;
import com.example.whatsappclone.models.Message;
import com.example.whatsappclone.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;

public class chatDetails extends AppCompatActivity {

    String profileName;
    FirebaseAuth auth;
    FirebaseUser user;

    String recieverNumber,senderNumber;
    DatabaseReference database;
    ActivityChatDetailsBinding binding;
    Message model;
    ArrayList<Message> messages ;

    //    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        binding = ActivityChatDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        recieverNumber = intent.getStringExtra("number");

        messages = new ArrayList<>();
        messages.add(new Message("Start chat",user.getPhoneNumber()));



        loadData();



        database.child("chats")
                .child(senderNumber)
                .child(recieverNumber)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messages.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Message model = dataSnapshot.getValue(Message.class);
                            messages.add(model);

                        }

                        ChattingAdapter adapter = new ChattingAdapter(messages,chatDetails.this);
                        binding.chatRecyclerview.setAdapter(adapter);
                        binding.chatRecyclerview.setLayoutManager(new LinearLayoutManager(chatDetails.this));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(chatDetails.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("myerror", "onCancelled: " + error.toString());

                    }
                });


        binding.sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = binding.messagebox.getText().toString();

                model = new Message(message, senderNumber);
                model.setTime(new Date().getTime());

                if (senderNumber != null) {


                    database.child("chats")
                            .child(senderNumber)
                            .child(recieverNumber)

                            .push().setValue(model)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    database.child("chats")
                                            .child(recieverNumber)
                                            .child(senderNumber)
                                            .push().setValue(model);
                                }
                            })
                            ;
                }
                binding.messagebox.setText("");
            }
        });



    }



    private void loadData() {
        Intent intent = getIntent();
        profileName=intent.getStringExtra("profileName");

        recieverNumber=intent.getStringExtra("number");
        recieverNumber=recieverNumber.replaceAll("\\s", "");

        senderNumber = user.getPhoneNumber();
        senderNumber=senderNumber.replaceAll("\\s", "");

        if(senderNumber.length()>10)
            senderNumber = senderNumber.substring(3);

        if(recieverNumber.length()>10)
            recieverNumber = recieverNumber.substring(3);


        binding.profileName.setText(profileName);
        binding.number.setText(recieverNumber);
    }
}
