
package com.netcracker.entity.helper;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author maks
 */
public enum CarCategory {
    
    ECONOM(1), VAN(2), BUSINESS(3), CARGO(4);
    
    private int id;
    
    private static Map<Integer, CarCategory> map = new HashMap<Integer, CarCategory>();
        
    static {
        for (CarCategory categoryEnum : CarCategory.values()) {
            map.put(categoryEnum.id, categoryEnum);
        }
    }
    
    private CarCategory (final int categoryId){
        id = categoryId;
    }
    
    public static CarCategory valueOf(int categoryId){
        return map.get(categoryId);
    }
    
    public int getId (){
        return id;
    }
}
