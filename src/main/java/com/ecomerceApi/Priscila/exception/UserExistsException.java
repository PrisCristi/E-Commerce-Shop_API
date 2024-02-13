package com.ecomerceApi.Priscila.exception;

public class UserExistsException extends Throwable {
    public UserExistsException(String message) {
        super(message);
    }
    public UserExistsException(){
        super();
    }
}
