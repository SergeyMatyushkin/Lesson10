package com.example.lesson10;

public class MessageEntity {
    private String name;
    private String message;

    public MessageEntity(String name, String message){
        this.name = name;
        this.message = message;
    }
    public String getName(){
        return name;
    }

    public String getMessage(){
        return message;
    }
}
