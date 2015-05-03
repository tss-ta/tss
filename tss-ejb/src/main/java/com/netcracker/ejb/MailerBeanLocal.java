/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import java.io.IOException;

import javax.ejb.EJBLocalObject;

import org.json.JSONException;

import com.netcracker.entity.Driver;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;

/**
 *
 * @author Stanislav Zabielin
 */
public interface MailerBeanLocal extends EJBLocalObject {

	public void sendEmail(User user, String title, String msg);
	
	public void changeToUpdated(User user, TaxiOrder to);
	
	public void changeToRefused(User user, TaxiOrder to);
	
	public void changeToAssigned(User user, TaxiOrder to, Driver driver);
	
	public void changeToCompleted(User user, TaxiOrder to);

}
