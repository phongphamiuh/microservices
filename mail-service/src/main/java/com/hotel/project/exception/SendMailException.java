package com.hotel.project.exception;

public class SendMailException extends RuntimeException{
    public SendMailException(String s){
        super(s);
    }
}
