/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

/**
 *
 * @author Lis
 */
public interface ConveyCorpServiceBeanLocalHome extends EJBLocalHome {
    ConveyCorpServiceBeanLocal create();
}