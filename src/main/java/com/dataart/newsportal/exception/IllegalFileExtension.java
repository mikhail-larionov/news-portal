package com.dataart.newsportal.exception;

public class IllegalFileExtension extends RuntimeException{
    public IllegalFileExtension(String message){
        super(message);
    }
}
