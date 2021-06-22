package com.example.lesson10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private EditText nameEt;
    private EditText messageEt;
    private ImageView sendIV;
    private RecyclerView recyclerView;
    private MessagesAdapter adapter;

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
    }
}