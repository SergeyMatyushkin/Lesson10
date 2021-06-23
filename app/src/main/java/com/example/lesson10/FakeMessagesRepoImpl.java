package com.example.lesson10;

import java.util.ArrayList;
import java.util.List;

public class FakeMessagesRepoImpl implements MessagesRepo{
    private List<MessageEntity> messages = new ArrayList<>();

    private List<Runnable> subscribers = new ArrayList<>();

    @Override
    public void sendMessage(MessageEntity message) {
        messages.add(message);
        notifySubscribers();

    }

    private void notifySubscribers() {
      for (Runnable subscriber : subscribers){
          subscriber.run();
      }
    }

    @Override
    public List<MessageEntity> getMessages() {
        return messages;
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
