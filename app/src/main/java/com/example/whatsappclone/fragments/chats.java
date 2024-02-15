package com.example.whatsappclone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whatsappclone.R;
import com.example.whatsappclone.adapters.UserAdapter;
import com.example.whatsappclone.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link chats#newInstance} factory method to
 * create an instance of this fragment.
 */
public class chats extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String mynum;

    String[] usr = {"Nikhil", "Nisarga", "Kundan", "Aniket", "user"};
    ArrayList<Message> messages = new ArrayList<>();
    ArrayList<String> numbers = new ArrayList<>();
    ArrayList<User> chats = new ArrayList<>();
    RecyclerView recyclerView;

    public chats() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment chats.
     */
    // TODO: Rename and change types and number of parameters
    public static chats newInstance(String param1, String param2) {
        chats fragment = new chats();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mynum = user.getPhoneNumber();
        if (mynum.length() > 10)
            mynum = mynum.substring(3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        recyclerView = view.findViewById(R.id.recycler);

        loadChatsData();

        Log.d("chteeffs", "onDataChange: "+chats);


        return view;
    }

    private void findUsers() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        for (String num:numbers) {
            Query q = database.child("users").orderByChild("mobile").equalTo(num);
            q.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot shot : snapshot.getChildren()) {
                                User user = shot.getValue(User.class);
                                chats.add(user);
                            }
                            Log.d("sdsdsds", "onDataChange: " + chats);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            UserAdapter adapter = new UserAdapter(chats, getContext()); // Create your adapter instance
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                            Log.d("chteeffs", "onDataChange: " + error);

                        }
                    });


        }
    }

    private void loadChatsData() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();


        database.child("chats")
                .child(mynum)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        numbers.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            numbers.add(dataSnapshot.getKey());
                            findUsers();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("xyzzisjdnsj", "onDataChange: " + error);

                    }
                });


//        database.child("users")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot shot:snapshot.getChildren()) {
//                            User user = shot.getValue(User.class);
//                            chats.add(user);
//                        }
//                        Log.d("chteeffs", "onDataChange: "+chats);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                        Log.d("chteeffs", "onDataChange: "+error);
//
//
//                    }
//                });
//


    }
}


