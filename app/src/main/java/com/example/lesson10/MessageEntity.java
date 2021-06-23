package com.example.lesson10;

public class MessageEntity {
    private String name;
    private String message;
    private String uid;
    private int color;

    public MessageEntity(){


    }

    public MessageEntity(String name, String message, String uid, int color){
        this.name = name;
        this.message = message;
        this.color = color;
        this.uid = uid;
    }
    public String getName(){
        return name;
    }

    public String getMessage(){
        return message;
    }

    public int getColor() {
        return color;
    }

    public String getUid() {
        return uid;
    }
}
