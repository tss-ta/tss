package com.netcracker.dao.exceptions;

public class NoSuchEntity extends Exception {

    public NoSuchEntity (){
        super ("No such entity founded");
    }

    public NoSuchEntity (Throwable cause){
        super ("No such entity founded", cause);
    }
    public NoSuchEntity (String message){
        super (message);
    }
    public NoSuchEntity (String message, Throwable cause){
        super (message, cause);
    }

}
