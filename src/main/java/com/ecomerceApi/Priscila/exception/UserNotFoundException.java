package com.ecomerceApi.Priscila.exception;

public class UserNotFoundException extends Throwable{
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(){
        super();
    }
}
