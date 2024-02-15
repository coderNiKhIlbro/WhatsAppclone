package com.example.whatsappclone.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.chatDetails;
import com.example.whatsappclone.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    ArrayList<User> arrayList;
    Context context;
    User u;

    public UserAdapter(ArrayList<User> array, Context context) {
        this.arrayList = array;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.userchats,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
             u = arrayList.get((position));
            holder.name.setText(u.getName());
            holder.msg.setText("");


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView msg;

        public CircleImageView profile;

        ViewHolder(View v){
            super(v);
            name = itemView.findViewById(R.id.username);
            msg =  itemView.findViewById(R.id.lastmsg);
            profile = itemView.findViewById(R.id.profile_image);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), chatDetails.class);
                    intent.putExtra("profileName",u.getName());
                    intent.putExtra("number",u.getMobile());
//                    intent.putExtra("userVal", user);
                    context.startActivity(intent);
                }
            });

        }
    }
}