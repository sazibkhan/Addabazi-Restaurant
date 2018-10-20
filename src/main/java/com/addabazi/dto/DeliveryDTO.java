package com.addabazi.dto;

import java.io.Serializable;

import com.addabazi.entity.CustomerBasicInfo;

public class DeliveryDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long deliveryID;
	private CustomerBasicInfo customerBasicInfo;
	private double totualAmount;
	
	
	public Long getDeliveryID() {
		return deliveryID;
	}
	public void setDeliveryID(Long deliveryID) {
		this.deliveryID = deliveryID;
	}
	public CustomerBasicInfo getCustomerBasicInfo() {
		if(customerBasicInfo==null) customerBasicInfo=new CustomerBasicInfo();
		return customerBasicInfo;
	}
	public void setCustomerBasicInfo(CustomerBasicInfo customerBasicInfo) {
		this.customerBasicInfo = customerBasicInfo;
	}
	public double getTotualAmount() {
		return totualAmount;
	}
	public void setTotualAmount(double totualAmount) {
		this.totualAmount = totualAmount;
	}

}
