package com.netcracker.entity.helper;

import com.netcracker.entity.TaxiOrder;

public class TaxiOrderHistory extends TaxiOrder {

	private String toAddr;
	private String fromAddr;
//	private String strStatus;

	public TaxiOrderHistory(TaxiOrder to) {
		super(to);
//		strStatus = to.convertStatusToEnum().toString();
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


}
