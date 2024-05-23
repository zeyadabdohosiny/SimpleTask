package com.example.simpletask.app.ui.activties.adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.simpletask.R;
import com.example.simpletask.domain.models.chat.ChatEntity;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ChatEntity> messageList = new ArrayList<>();
    OnItemClickListener mlistner;
    String userId;


    public interface OnItemClickListener {
        public void onUserClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mlistner = listener;
    }

    public ChatAdapter(List<ChatEntity> messageList, String userId) {
        this.messageList = messageList;
        this.userId = userId;
    }

    class MyChatsViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;

        public MyChatsViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUserClick(getAdapterPosition());
                }
            });
        }
    }

    class AnotherChatsViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;

        public AnotherChatsViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUserClick(getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (viewType == 1) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_sender_item, parent, false);
            MyChatsViewHolder viewHolder = new MyChatsViewHolder(v, mlistner);
            return viewHolder;
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_receiver_item, parent, false);
            AnotherChatsViewHolder viewHolder = new AnotherChatsViewHolder(v, mlistner);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatEntity message = messageList.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                AnotherChatsViewHolder anotherChatsViewHolder = (AnotherChatsViewHolder) holder;
                ((AnotherChatsViewHolder) holder).tvMessage.setText(message.getMessage());
                break;

            case 1:
                MyChatsViewHolder myChatsViewHolder = (MyChatsViewHolder) holder;
                ((MyChatsViewHolder) holder).tvMessage.setText(message.getMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position).getSenderId() == userId) {
            return 1;
        }
        return 0;
    }
}
