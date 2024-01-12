package com.ecomerceApi.Priscila.exception;

public class UserExistsExecption extends Throwable {
    public UserExistsExecption(String message) {
        super(message);
    }
    public UserExistsExecption(){
        super();
    }
}
