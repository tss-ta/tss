package com.netcracker.entity.helper;

import com.netcracker.entity.TaxiOrder;

public class TaxiOrderHistory extends TaxiOrder {

	private String toAddr;
	private String fromAddr;

	public TaxiOrderHistory(TaxiOrder to) {
		super(to);
		
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

}
