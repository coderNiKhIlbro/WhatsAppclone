package com.example.whatsappclone.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.models.Message;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ChattingAdapter extends RecyclerView.Adapter {


    ArrayList<Message> messages;
    Context context;
    FirebaseAuth auth;
    String senderNumber ;


    int SENDER_VIEW_TYPE = 1;
    int RECIEVER_VIEW_TYPE = 2;


    public ChattingAdapter(ArrayList<Message> messages, Context context) {
        Log.d("sdsdddddddd", "ChattingAdapter: " + messages);
        this.messages = messages;
        this.context = context;

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        senderNumber = user.getPhoneNumber();

        if(senderNumber.length()>10)
            senderNumber = senderNumber.substring(3);

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("TAG1", "onCreateViewHolder: ");

        if (viewType == SENDER_VIEW_TYPE) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sending_message_layout, parent, false);
            return new SenderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recieving_message_layout, parent, false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Log.d("TAG1", "onBindViewHolder: ");
        Message message = messages.get(position);

        if (holder.getClass() == SenderViewHolder.class) {
            ((SenderViewHolder) holder).sendingMessage.setText(message.getMessage());
//            ((SenderViewHolder)holder).time.setText(message.getMessage());
        } else {
            ((RecieverViewHolder) holder).recievingMessage.setText(message.getMessage());

        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (messages.get(position).getuId().equals(senderNumber)) {
            return SENDER_VIEW_TYPE;
        }
        else
            return RECIEVER_VIEW_TYPE;
    }

    class RecieverViewHolder extends RecyclerView.ViewHolder{


        TextView recievingMessage,time;

        public RecieverViewHolder(@NonNull View itemView) {

            super(itemView);
            recievingMessage =itemView.findViewById(R.id.recievingMessage);
            time = itemView.findViewById(R.id.recievingTime);
        }

    }

    class SenderViewHolder extends  RecyclerView.ViewHolder{

        TextView sendingMessage,time;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            sendingMessage =itemView.findViewById(R.id.sendingMessage);
            time = itemView.findViewById(R.id.sendingTime);

        }
    }
}
