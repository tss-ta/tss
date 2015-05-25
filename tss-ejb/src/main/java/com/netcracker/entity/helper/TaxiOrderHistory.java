package com.netcracker.entity.helper;

import java.util.List;

import com.netcracker.dao.CelebrationServiceDAO;
import com.netcracker.dao.DriverDAO;
import com.netcracker.dao.ServiceDAO;
import com.netcracker.ejb.CelebrationServiceBean;
import com.netcracker.entity.CelebrationService;
import com.netcracker.entity.Route;
import com.netcracker.entity.Service;
import com.netcracker.entity.TaxiOrder;

public class TaxiOrderHistory extends TaxiOrder {

	private String toAddr;
	private String fromAddr;

	public TaxiOrderHistory(TaxiOrder to) {
		super(to);
		findServiceName();
	}

	public String getToAddr() {
		return toAddr;
	}

	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

	public String getStrStatus() {
		return getEnumStatus().toString();
	}

	public boolean isServiceBool() {
		if (getService() != null)
			return true;
		else
			return false;
	}

	public boolean isConvey() {
		if (!isServiceBool())
			return false;
		Service serv = getService();
		if (serv.getServiceName().equals("conveycorp"))
			return true;
		else
			return false;
	}

	public boolean isMeetMyGuest() {
		if (!isServiceBool())
			return false;
		Service serv = getService();
		if (serv.getServiceName().equals("meetMyGuest"))
			return true;
		else
			return false;
	}
	
	public boolean isSoberDriver() {
		if (!isServiceBool())
			return false;
		Service serv = getService();
		if (serv.getServiceName().equals("sober"))
			return true;
		else
			return false;
	}
	
	public boolean isCelebration() {
		if (!isServiceBool())
			return false;
		Service serv = getService();
		if (serv.getServiceName().equals("celebration"))
			return true;
		else
			return false;
	}

	public List<Route> getAddrConvey() {
		if (!isConvey())
			return null;
		Service serv = getService();
		return serv.getRoutes();
	}

	private Service getService() {
		ServiceDAO dao = null;
		try {
			dao = new ServiceDAO();
			if (dao.getByOrderId(getId()) != null)
				return dao.getByOrderId(getId());
			return null;
		} finally {
			if (dao != null)
				dao.close();
		}
	}

	public String getServiceName() {
		return findServiceName();
	}

	public String findServiceName() {
		if (isConvey())
			return "Convey Service";
		if (isMeetMyGuest())
			return "Meet My Guest";
		if (isSoberDriver())
			return "Sober Driver";
		if (isCelebration())
			return "Celebration Taxi";
		return "";
	}
}
