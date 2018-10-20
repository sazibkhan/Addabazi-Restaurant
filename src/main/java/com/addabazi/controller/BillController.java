package com.addabazi.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.addabazi.dto.BillDTO;
import com.addabazi.dto.CustomerInfoDTO;
import com.addabazi.dto.DeliveryDTO;
import com.addabazi.dto.SellDetailsDTO;
import com.addabazi.dto.ServiceDTO;
import com.addabazi.entity.CustomerBasicInfo;
import com.addabazi.entity.SellDetails;
import com.addabazi.entity.ServiceEntity;
import com.addabazi.jasper.JasperUtil;
import com.addabazi.service.BillService;
import com.addabazi.service.ServicesService;

@Controller
@ManagedBean
@Scope("session")
public class BillController implements Serializable{
	
	private static final long serialVersionUID = 1L;
			
	private BillDTO BillDTO;
	private CustomerInfoDTO customerInfoDTO;
	private SellDetailsDTO sellDetailsDTO;
	
	private ServiceDTO serviceDTO;
	private DeliveryDTO deliveryDTO;

	private int totualAmount;
	private int finalAmount=0;
	private String CutomerName;
	private int Number;
	private String CustomerID;
	private Long CustID;
	
	
	private int test;
	
	private List<SellDetailsDTO> sellDetailsDTOList;
	private List<ServiceDTO> itemList;
	private List<CustomerInfoDTO> customerDtoList;

	
	@Autowired
	private BillService billService;
	
	@Autowired
	private ServicesService servicesService;	
	
	
	public void resetList(){
		itemList=new ArrayList<>();
	}
	
	public void saveSellDetails(){	
		List<SellDetails> sellDetailsList=new ArrayList<>();
		CustomerBasicInfo customerBasicInfo=new CustomerBasicInfo();
		customerBasicInfo=saveCustomerBasicInfo();	
	
		
		for(ServiceDTO dto:itemList){				
			 SellDetails sellDetails=new SellDetails();
			 ServiceEntity service=new ServiceEntity();			
			 service.setServiceID(dto.getServiceEntity().getServiceID());			 
			 sellDetails.setServiceEntity(service);		
			 sellDetails.setCustomerBasicInfo(customerBasicInfo);
			 sellDetails.setQuantity(dto.getQuantity());
			 sellDetails.setRate(dto.getAmount());
			 sellDetailsList.add(sellDetails);	
			 dto=null;
			
		}
		
		billService.saveSellDetails(sellDetailsList);
		findIdWiseCustomer();
		sellDetailsList=null;
	}
	
	
	public List<SellDetails> addItem(){
		//List<SellDetails> sellDetailsList=new ArrayList<>();
		if(itemList==null) itemList=new ArrayList<>();
		try{
			
			serviceDTO=new ServiceDTO();
			ServiceEntity serviceEntity=new ServiceEntity();
			serviceEntity=servicesService.findService(sellDetailsDTO.getServiceEntity().getServiceID());	
			serviceDTO.setServiceEntity(serviceEntity);
			serviceDTO.setServiceName(serviceEntity.getServiceName());
			serviceDTO.setServiceID(serviceEntity.getServiceID());
			serviceDTO.setPrice(serviceEntity.getPrice());
			serviceDTO.setQuantity(sellDetailsDTO.getQuantity());
			serviceDTO.setSellNo(itemList.size()+1);
			totualAmount=serviceEntity.getPrice()*sellDetailsDTO.getQuantity();
			serviceDTO.setAmount(totualAmount);
			itemList.add(serviceDTO);
			sellDetailsDTO=null;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finalAmount=(finalAmount+totualAmount);	
		
		return null;
	}
	
	
	
	public CustomerBasicInfo saveCustomerBasicInfo(){
		CustomerBasicInfo customerBasicInfo=new CustomerBasicInfo();
		customerInfoDTO.setCustomerID(makeCustomerID());
		customerInfoDTO.setDateTime(new Date());
		customerInfoDTO.setToutalamount(finalAmount);
		customerBasicInfo=billService.billBasicInfo(customerInfoDTO);
		CustID=customerBasicInfo.getCustID();
		
		return customerBasicInfo;
	}
	
	
	public String userBasicInfo(){
		try{
		saveSellDetails();
		CutomerName=customerInfoDTO.getCustomerName();
		Number=customerInfoDTO.getCustomerNumber();
		CustomerID=makeCustomerID();
		customerInfoDTO=null;
		}catch (Exception e) {
		e.printStackTrace();
		}
		return "billEntry-process-2.xhtml?faces-redirect=true";
	}

	
	public void findIdWiseCustomer(){	
		System.out.println("custome ris working..."+CustID);
		sellDetailsDTOList=new ArrayList<>();
		sellDetailsDTOList=billService.findAllItem(CustID);
		
	}
	
	
	
	
	public String searchpatient(){
		List<CustomerBasicInfo> customerBasicInfos=new ArrayList<>();
			customerDtoList=new ArrayList<>();
		if(customerInfoDTO.getCustomerID()!=null){
		try{
			customerBasicInfos=billService.findCustomer(customerInfoDTO.getCustomerID());
		
			for(CustomerBasicInfo c:customerBasicInfos){
				CustomerInfoDTO customerInfoDTO=new CustomerInfoDTO();
				customerInfoDTO.setCustomerID(c.getCustomerID());
				customerInfoDTO.setCustomerName(c.getCustomerName());
				customerInfoDTO.setCustomerNumber(c.getCustomerNumber());
				customerInfoDTO.setDateTime(c.getDateTime());
				customerInfoDTO.setToutalamount(c.getToutalamount());
				customerDtoList.add(customerInfoDTO);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			}else {
				customerDtoList=new ArrayList<>();
				customerDtoList=billService.findAllCustomer();

			}
		return null;
		
		
	}
	
	public String makeCustomerID(){
		long currentId=9+new Date().getTime();
		String customerId="AD"+currentId;
		return customerId;	
	}
	
			public String downloadAllStudents(){
			  addItem();
			Map<String, Object> map=new HashMap();
			map.put("restaurantName", "Addabazi Restaurant");
			JasperUtil jasperUtil=new JasperUtil();	 
			try{
				jasperUtil.jasperPrint(itemList, map, "com/addabazi/report/BillReport.jasper", "Bill Details");
			}catch(Exception e){
		            e.printStackTrace();
		            
			}
			itemList=new ArrayList<>();
			
			return "billEntry-process-1.xhtml?faces-redirect=true";
		}
 
	
	public BillDTO getBillDTO() {
		if(BillDTO==null){
			BillDTO=new BillDTO();
		}
		return BillDTO;
	}

	public void setBillDTO(BillDTO BillDTO) {
		this.BillDTO = BillDTO;
	}

	public CustomerInfoDTO getCustomerInfoDTO() {
		if(customerInfoDTO==null){
			customerInfoDTO=new CustomerInfoDTO();
		}
		return customerInfoDTO;
	}

	public void setCustomerInfoDTO(CustomerInfoDTO customerInfoDTO) {
		this.customerInfoDTO = customerInfoDTO;
	}


	public ServiceDTO getServiceDTO() {
		if(serviceDTO==null){
			serviceDTO=new ServiceDTO();
		}
		return serviceDTO;
	}

	public void setServiceDTO(ServiceDTO serviceDTO) {
		this.serviceDTO = serviceDTO;
	}


	public SellDetailsDTO getSellDetailsDTO() {
		if(sellDetailsDTO==null){
			sellDetailsDTO=new SellDetailsDTO();
		}
		return sellDetailsDTO;
	}


	public void setSellDetailsDTO(SellDetailsDTO sellDetailsDTO) {
		this.sellDetailsDTO = sellDetailsDTO;
	}

	public List<SellDetailsDTO> getSellDetailsDTOList() {
			findIdWiseCustomer();
		return sellDetailsDTOList;
	}

	public void setSellDetailsDTOList(List<SellDetailsDTO> sellDetailsDTOList) {
		this.sellDetailsDTOList = sellDetailsDTOList;
	}

	public List<ServiceDTO> getItemList() {
		return itemList;
	}

	public void setItemList(List<ServiceDTO> itemList) {
		this.itemList = itemList;
	}


	public int getFinalAmount() {
		return finalAmount;
	}


	public void setFinalAmount(int finalAmount) {
		this.finalAmount = finalAmount;
	}
	
	
	public String getCutomerName() {
		return CutomerName;
	}

	public void setCutomerName(String cutomerName) {
		CutomerName = cutomerName;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public DeliveryDTO getDeliveryDTO() {
		if(deliveryDTO==null) deliveryDTO=new DeliveryDTO();
		return deliveryDTO;
	}

	public void setDeliveryDTO(DeliveryDTO deliveryDTO) {
		this.deliveryDTO = deliveryDTO;
	}

	public List<CustomerInfoDTO> getCustomerDtoList() {
		searchpatient();
		return customerDtoList;
	}

	public void setCustomerDtoList(List<CustomerInfoDTO> customerDtoList) {
		this.customerDtoList = customerDtoList;
	}

	public Long getCustID() {
		return CustID;
	}

	public void setCustID(Long custID) {
		CustID = custID;
	}


}
