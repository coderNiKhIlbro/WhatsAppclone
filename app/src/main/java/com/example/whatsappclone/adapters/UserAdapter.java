package com.example.whatsappclone.adapters;

import android.content.Context;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    ArrayList<String> arrayList;
    Context context;

    public UserAdapter(ArrayList<String> array, Context context) {
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
            holder.name.setText(arrayList.get(position));
            holder.msg.setText("messgae");


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

        }
    }
}