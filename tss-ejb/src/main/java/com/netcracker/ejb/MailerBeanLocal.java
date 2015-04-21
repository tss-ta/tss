/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import java.util.List;
import javax.ejb.EJBLocalObject;
import javax.ejb.Local;

/**
 *
 * @author maks
 */
@Local
public interface MailerBeanLocal extends EJBLocalObject{
    
    boolean sendMail(String emailAddres, String message);
//    boolean sendMail(String [] emailAddreses, String message);
//    boolean sendMail(List <String> emailAddreses, String message);
}
