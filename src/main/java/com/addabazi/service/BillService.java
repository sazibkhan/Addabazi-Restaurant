package com.addabazi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addabazi.dto.CustomerInfoDTO;
import com.addabazi.dto.DeliveryDTO;
import com.addabazi.dto.SellDetailsDTO;
import com.addabazi.entity.CustomerBasicInfo;
import com.addabazi.entity.DeliveryEntity;
import com.addabazi.entity.SellDetails;
import com.addabazi.repo.BillRepo;
import com.addabazi.repo.CustomerRepo;
import com.addabazi.repo.DeliveryRepo;
import com.addabazi.repo.ManagerRepo;
import com.addabazi.repo.SellRepo;
import com.addabazi.repo.ServiceRepo;

@Service
@Transactional
public class BillService {
	
	@Autowired
	private CustomerRepo CustomerRepo;
	
	@Autowired
	private SellRepo sellRepo;
	
	
	@Autowired
	private DeliveryRepo deliveryRepo;
	
	
	  
	  public CustomerBasicInfo billBasicInfo(CustomerInfoDTO customerBasicInfo){
		  CustomerBasicInfo entity=new CustomerBasicInfo();		  
		  BeanUtils.copyProperties(customerBasicInfo, entity);
		  entity=CustomerRepo.save(entity);
		  return entity;
		  
	  }
	  
	public List<SellDetailsDTO> findAllItem(){
		List<SellDetailsDTO> dtoList=new ArrayList<>();
		List<SellDetails> list=new ArrayList<>();
		list=sellRepo.findAll();
		for(SellDetails s:list){
			SellDetailsDTO dto=new SellDetailsDTO();
			BeanUtils.copyProperties(s, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	public void saveSellDetails(List<SellDetails> sellDetailsList) {
		sellRepo.save(sellDetailsList);
	}

	public void FinalAddData(DeliveryDTO deliveryDTO) {
	
		DeliveryEntity deliveryEntity=new DeliveryEntity();
		BeanUtils.copyProperties(deliveryDTO, deliveryEntity);
		deliveryRepo.save(deliveryEntity);
		
	}

	public List<CustomerInfoDTO> findAllCustomer() {
		List<CustomerInfoDTO> dtolist=new ArrayList<>();
		List<CustomerBasicInfo> list=new ArrayList<>();
		list=CustomerRepo.findAll();
		for(CustomerBasicInfo c:list){
			CustomerInfoDTO customerInfoDTO=new CustomerInfoDTO();
			BeanUtils.copyProperties(c, customerInfoDTO);
			dtolist.add(customerInfoDTO);
		}
		return dtolist;
	}

	public List<CustomerBasicInfo> findCustomer(String customerID) throws Exception{
		return CustomerRepo.findCustomer(customerID);
	}

	public List<SellDetailsDTO> findAllItem(Long custID) {
		List<SellDetailsDTO> dtoList=new ArrayList<>();
		List<SellDetails> list=new ArrayList<>();
		list=sellRepo.findIdWiseCustomer(custID);
		for(SellDetails s:list){
			SellDetailsDTO sellDetailsDTO=new SellDetailsDTO();
			BeanUtils.copyProperties(s, sellDetailsDTO);
			dtoList.add(sellDetailsDTO);
		}
		
		return dtoList;
	}

}
