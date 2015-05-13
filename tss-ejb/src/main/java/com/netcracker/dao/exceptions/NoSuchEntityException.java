package com.netcracker.dao.exceptions;

public class NoSuchEntityException extends Exception {

    public NoSuchEntityException(){
        super ("No such entity founded");
    }

    public NoSuchEntityException(Throwable cause){
        super ("No such entity founded", cause);
    }
    public NoSuchEntityException(String message){
        super (message);
    }
    public NoSuchEntityException(String message, Throwable cause){
        super (message, cause);
    }

}
