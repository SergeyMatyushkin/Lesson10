package com.example.lesson10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private EditText nameEt;
    private EditText messageEt;
    private ImageView sendIV;
    private RecyclerView recyclerView;
    private MessagesAdapter adapter;
    private MessagesRepo repo;

    private Runnable subscriber = () ->{
        updateAllMessages();
    };

    private String uid = UUID.randomUUID().toString();
    private int color = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEt = findViewById(R.id.nickname_edit_text);
        messageEt = findViewById(R.id.message_edit_text);
        sendIV = findViewById(R.id.send_button);
        recyclerView = findViewById(R.id.recycler);

        adapter = new MessagesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        repo = new FirebaseMessagesRepoImpl();

        updateAllMessages();

        sendIV.setOnClickListener(v -> {
            MessageEntity message = new MessageEntity(nameEt.getText().toString(), messageEt.getText().toString(),
                    uid, color);
            repo.sendMessage(message);
            messageEt.setText("");
        });

        repo.subscribe(subscriber);
    }

    @Override
    protected void onDestroy(){
        repo.unsubscribe(subscriber);
        super.onDestroy();

    }

    private void updateAllMessages(){
        adapter.setData(repo.getMessages(), uid);
        adapter.notifyDataSetChanged();
    }
}