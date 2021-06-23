package com.example.lesson10;

import java.util.List;

public interface MessagesRepo {
    void sendMessage(MessageEntity message);
    List<MessageEntity> getMessages();

    void subscribe(Runnable subscriber);

    void unsubscribe(Runnable subscriber);




}
