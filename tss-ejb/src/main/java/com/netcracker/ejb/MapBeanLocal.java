/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import java.io.IOException;
import javax.ejb.EJBLocalObject;
import org.json.JSONException;

/**
 *
 * @author Виктор
 */
public interface MapBeanLocal extends EJBLocalObject {

    public double[] geocodeAddress(String address) throws JSONException, IOException;
    
    public String geodecodeAddress(double lng, double lat) throws JSONException, IOException;
}
