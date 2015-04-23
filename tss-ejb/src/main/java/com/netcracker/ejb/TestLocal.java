package com.netcracker.ejb;

import javax.ejb.EJBLocalObject;

public interface TestLocal extends EJBLocalObject{
    String sayHello();
}