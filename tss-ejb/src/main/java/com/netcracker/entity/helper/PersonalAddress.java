package com.netcracker.entity.helper;

import java.io.IOException;

import org.json.JSONException;

import com.netcracker.ejb.MapBean;
import com.netcracker.entity.Address;

public class PersonalAddress {
	
	private String addr;
	
	public PersonalAddress(Address a) {
		MapBean mapBean = new MapBean();
		try {
			setAddr(mapBean.geodecodeAddress(a.getAltitude(), a.getLongtitude()));
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
