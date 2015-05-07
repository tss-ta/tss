package com.netcracker.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * Created by Illya on 06.05.2015.
 */
public interface CelebrationServiceBeanLocalHome extends EJBLocalHome {
    com.netcracker.ejb.CelebrationServiceBeanLocal create() throws CreateException;
}
