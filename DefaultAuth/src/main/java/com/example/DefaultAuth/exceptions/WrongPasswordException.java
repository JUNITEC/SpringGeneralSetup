package com.example.DefaultAuth.exceptions;


public class WrongPasswordException extends RuntimeException{

    private String username;
    public WrongPasswordException(String username){
        super("Wrong password for " + username);
        this.username = username;
    }

    public String getUsername(){return this.username;}
}