package com.addabazi.dto;

import java.io.Serializable;

import com.addabazi.entity.CustomerBasicInfo;
import com.addabazi.entity.ServiceEntity;

public class SellDetailsDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	private Long sellID;
	private CustomerBasicInfo customerBasicInfo;
	private ServiceEntity serviceEntity;
	private int quantity;
	private double rate;
	
	
	public Long getSellID() {
		return sellID;
	}
	public void setSellID(Long sellID) {
		this.sellID = sellID;
	}
	public ServiceEntity getServiceEntity() {
		if(serviceEntity==null)serviceEntity=new ServiceEntity();
		return serviceEntity;
	}
	public void setServiceEntity(ServiceEntity serviceEntity) {
		this.serviceEntity = serviceEntity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public CustomerBasicInfo getCustomerBasicInfo() {
		return customerBasicInfo;
	}
	public void setCustomerBasicInfo(CustomerBasicInfo customerBasicInfo) {
		this.customerBasicInfo = customerBasicInfo;
	}
	
	
	
}
