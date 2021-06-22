package com.example.lesson10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageVh> {

    private List<MessageEntity> data = new ArrayList();


    public void setData(List<MessageEntity> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MessageVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageVh(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MessageVh holder, int position) {
        holder.bind(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MessageVh extends RecyclerView.ViewHolder{
        private TextView nameTv = itemView.findViewById(R.id.name_text_view);
        private TextView messageTv = itemView.findViewById(R.id.message_text_view);

        public MessageVh(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false));

        }

        public void bind(MessageEntity messageEntity) {
            nameTv.setText(messageEntity.getName());
            messageTv.setText(messageEntity.getMessage());

        }
    }
}
