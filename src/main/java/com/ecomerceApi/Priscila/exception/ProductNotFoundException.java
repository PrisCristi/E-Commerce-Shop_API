package com.ecomerceApi.Priscila.exception;

public class ProductNotFoundException extends Throwable{  // check if  "throwable" is okay.

    public ProductNotFoundException (String message){
        super(message);
    }
}
