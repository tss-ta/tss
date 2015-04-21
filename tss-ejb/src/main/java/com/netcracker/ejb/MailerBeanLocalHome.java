/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

/**
 *
 * @author maks
 */
public interface MailerBeanLocalHome extends EJBLocalHome {
    MailerBeanLocal create();
}
