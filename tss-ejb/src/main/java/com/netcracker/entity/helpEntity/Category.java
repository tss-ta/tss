package com.netcracker.entity.helpEntity;

/**
 * Created by Illya on 25.04.2015.
 */
public enum Category {
    B("B"), C("C"), D("D");

    private final String catStr;

    Category(String catStr) {
        this.catStr = catStr;
    }

    public String getCatStr() {
        return catStr;
    }
}
