package com.netcracker.ejb;



import com.netcracker.dao.TaxiOrderDAO;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.persistence.NoResultException;

/**
 * 
 * @author maks
 */
public class ReportsBean implements SessionBean {
    
        
    public int countOrdersWithWifi (boolean haveWifi){
        TaxiOrderDAO dao = null;
        try {
            dao = new TaxiOrderDAO();
            return dao.countOrdersWithWifi(haveWifi);
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
    }



    @Override
    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }

}
