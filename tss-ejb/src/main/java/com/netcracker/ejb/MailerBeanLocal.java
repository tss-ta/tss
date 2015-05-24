/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import java.io.IOException;

import javax.ejb.EJBLocalObject;

import org.json.JSONException;

import com.netcracker.entity.Contacts;
import com.netcracker.entity.Driver;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;

/**
 *
 * @author Stanislav Zabielin
 */
public interface MailerBeanLocal extends EJBLocalObject {

	public void sendEmail(Contacts user, String title, String msg);
        
	public void sendEmail(String emailAddress, String title, String msg);
	
	public void changeToUpdated(Contacts user, TaxiOrder to);
	
	public void changeToRefused(Contacts user, TaxiOrder to);
	
	public void changeToAssigned(Contacts user, TaxiOrder to, String string);
	
	public void changeToCompleted(Contacts user, TaxiOrder to);
    @Deprecated
	public void sendToken(String emailAddress, Integer token);

    void sendDriverInvite(String email, String signupURL);

}
