package com.addabazi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sell_details")
public class SellDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sellID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sellID;
	
	@ManyToOne
	@JoinColumn(name="custID")
	private CustomerBasicInfo customerBasicInfo;
	
	@ManyToOne
	@JoinColumn(name="serviceID")
	private ServiceEntity serviceEntity;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="rate")
	private double rate;

	
	public int getSellID() {
		return sellID;
	}

	public void setSellID(int sellID) {
		this.sellID = sellID;
	}
	

	public ServiceEntity getServiceEntity() {
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
