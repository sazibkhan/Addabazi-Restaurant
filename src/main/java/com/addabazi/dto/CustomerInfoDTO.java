package com.addabazi.dto;

import java.io.Serializable;
import java.util.Date;

import com.addabazi.entity.ManagerEntity;

public class CustomerInfoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private Long custID;
	private String customerID;
	private String customerName;
	private int customerNumber;	
	private Date dateTime;
	private ManagerEntity managerEntity;
	private double toutalamount;
	
	
	public Long getCustID() {
		return custID;
	}
	public void setCustID(Long custID) {
		this.custID = custID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public ManagerEntity getManagerEntity() {
		if(managerEntity==null) managerEntity=new ManagerEntity();
		return managerEntity;
	}
	public void setManagerEntity(ManagerEntity managerEntity) {
		this.managerEntity = managerEntity;
	}
	public double getToutalamount() {
		return toutalamount;
	}
	public void setToutalamount(double toutalamount) {
		this.toutalamount = toutalamount;
	}
	
	
}
