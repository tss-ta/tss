/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.util.reports;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author maks
 */
public class ValueMapComparator implements Comparator<String>{
    
    Map <String, Long> map;
    
    public ValueMapComparator (Map <String, Long> map){
        this.map = map;
    }

    @Override
    public int compare(String o1, String o2) {
        return (int) (map.get(o1)-map.get(o2));
    }
    
}
