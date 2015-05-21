/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.dao;

import javax.persistence.TypedQuery;

import org.apache.poi.ss.formula.functions.T;

import com.netcracker.ejb.TaxiOrderBean;
import com.netcracker.entity.Contacts;
import com.netcracker.entity.Service;

/**
 *
 * @author Lis
 */
public class ServiceDAO extends GenericDAO<Service> {
	public ServiceDAO() {
		super();
	}

	public Service getByOrderId(int orderId) {
		TaxiOrderBean bean = new TaxiOrderBean();
		TypedQuery<Service> query = em.createNamedQuery(
				"Service.findByOrderId", Service.class);
		query.setParameter("orderId", bean.getOrderById(orderId));
		Service serv = query.getSingleResult();
		if (serv.getOrderId().equals(bean.getOrderById(orderId)))
			return serv;
		return null;
	}
}
