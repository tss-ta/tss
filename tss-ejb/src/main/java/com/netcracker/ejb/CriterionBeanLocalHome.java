package com.netcracker.ejb;

import javax.ejb.EJBLocalHome;

/**
 * @author Kyrylo Berehovyi
 */
public interface CriterionBeanLocalHome extends EJBLocalHome {

    CriterionBeanLocal create();

}
