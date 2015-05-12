package com.netcracker.entity.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maks on 11.05.15.
 */
public enum Status {

    QUEUED(0), UPDATED(1), ASSIGNED(2), REFUSED(3), IN_PROGRESS(4), COMPLETED(5);

//    private final Integer status;
//
//    Status(Integer status) {
//        this.status = status;
//    }
//
//    public Integer convertToInteger() {
//        return status;
//    }


    private int id;

    private static Map<Integer, Status> map = new HashMap<Integer, Status>();

    static {
        for (Status status : Status.values()) {
            map.put(status.id, status);
        }
    }

    private Status (final int id){
        this.id = id;
    }

    public static Status valueOf(int id){
        return map.get(id);
    }

    public int getId (){
        return id;
    }
}
