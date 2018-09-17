package com.rem.fortune.dao;

import java.util.List;

import com.rem.fortune.model.CustomerSupplier;

public interface SupplierDao {
	public CustomerSupplier getById(int id) throws Exception; 
	public int create(CustomerSupplier custSupp) throws Exception;
	public List<CustomerSupplier> getAll(int isCustomer) throws Exception;
	public int deleteById(int id) throws Exception;
	public int updateById(CustomerSupplier custSupp) throws Exception;
	public int updateAddressById(CustomerSupplier custSupp) throws Exception;
}
