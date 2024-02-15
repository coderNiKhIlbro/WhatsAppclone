package com.example.whatsappclone.adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.ContactActivity;
import com.example.whatsappclone.R;
import com.example.whatsappclone.chatDetails;
import com.example.whatsappclone.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{
    ArrayList<User> contactList;
    Context context;

    public ContactsAdapter(ArrayList<User> array, Context context) {
        this.contactList = array;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userchats,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User contact = contactList.get(position);
        holder.name.setText(contact.getName());
        holder.msg.setText(contact.getMobile());
        holder.user=contact;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView msg;
        public CircleImageView profile;

        public  User user;

        ViewHolder(View v){
            super(v);

            name = itemView.findViewById(R.id.username);
            msg =  itemView.findViewById(R.id.lastmsg);
            profile = itemView.findViewById(R.id.profile_image);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createNewChat();
                }
            });


        }

        private void createNewChat() {
            Intent intent = new Intent(itemView.getContext(), chatDetails.class);
            intent.putExtra("profileName",name.getText());
            intent.putExtra("number",msg.getText());
//            intent.putExtra("userVal", user);
            context.startActivity(intent);


        }
    }
}