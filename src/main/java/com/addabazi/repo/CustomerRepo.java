package com.addabazi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.addabazi.entity.CustomerBasicInfo;

public interface CustomerRepo extends JpaRepository<CustomerBasicInfo, Long>{

	
	@Query(value="select t from CustomerBasicInfo t where t.customerID=?1")
	List<CustomerBasicInfo> findCustomer(String customerID)throws Exception;
	

}
