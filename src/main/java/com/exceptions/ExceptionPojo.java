package com.exceptions;

/**
 * Created by ryandesmond on 7/30/18.
 */
public class ExceptionPojo {

    String message;

    public ExceptionPojo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
