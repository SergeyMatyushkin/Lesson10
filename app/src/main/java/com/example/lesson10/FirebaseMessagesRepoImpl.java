package com.example.lesson10;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseMessagesRepoImpl implements MessagesRepo{
    private static final String MESSAGES_TABLE_NAME = "messages";
    private List<MessageEntity> cache = new ArrayList<>();

    private List<Runnable> subscribers = new ArrayList<>();

    private FirebaseFirestore db;

    public FirebaseMessagesRepoImpl(){
        db = FirebaseFirestore.getInstance();
        db.collection(MESSAGES_TABLE_NAME).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    refillCache(queryDocumentSnapshots);
            }
        });
        db.collection(MESSAGES_TABLE_NAME).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                refillCache(value);
            }
        });
    }

    private void refillCache(@Nullable QuerySnapshot snapshot){
        if (snapshot == null) return;
        cache = new ArrayList<>();
        for (DocumentSnapshot document : snapshot.getDocuments()){
            cache.add(document.toObject(MessageEntity.class));
        }
        notifySubscribers();

    }



    @Override
    public void sendMessage(MessageEntity message) {
        db.collection(MESSAGES_TABLE_NAME).add(message);

    }

    private void notifySubscribers() {
        for (Runnable subscriber : subscribers){
            subscriber.run();
        }
    }

    @Override
    public List<MessageEntity> getMessages() {
        // todo
        return cache;
    }

    @Override
    public void subscribe(Runnable subscriber) {
        subscribers.add(subscriber);

    }

    @Override
    public void unsubscribe(Runnable subscriber) {
        subscribers.remove(subscriber);

    }
}
