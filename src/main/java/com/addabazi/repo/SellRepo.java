package com.addabazi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.addabazi.entity.SellDetails;

public interface SellRepo extends JpaRepository<SellDetails, Long>{

	@Query(value="select t from SellDetails t where t.customerBasicInfo.custID=?1")
	public List<SellDetails> findIdWiseCustomer(Long custID); 

}
