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
@Table(name="delivery")
public class DeliveryEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="del_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long deliveryID;
	
	@ManyToOne
	@JoinColumn(name="custID")
	private CustomerBasicInfo customerBasicInfo;
	
	@Column(name="amount")
	private double totualAmount;
	
	public Long getDeliveryID() {
		return deliveryID;
	}
	public void setDeliveryID(Long deliveryID) {
		this.deliveryID = deliveryID;
	}
	public CustomerBasicInfo getCustomerBasicInfo() {
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
