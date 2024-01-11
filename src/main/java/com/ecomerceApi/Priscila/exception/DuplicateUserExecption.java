package com.ecomerceApi.Priscila.exception;

public class DuplicateUserExecption extends Throwable {
    public DuplicateUserExecption (String message) {
        super(message);
    }
    public DuplicateUserExecption(){
        super();
    }
}
