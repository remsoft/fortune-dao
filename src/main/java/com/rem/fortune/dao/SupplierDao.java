package com.rem.fortune.dao;

import java.util.List;

import com.rem.fortune.model.CustomerSupplier;

public interface SupplierDao {
	public CustomerSupplier getById(int id); 
	public int create(CustomerSupplier custSupp);
	public List<CustomerSupplier> getAll(int isCustomer);
	public int deleteById(int id);
	public int updateById(CustomerSupplier custSupp);
	public int updateAddressById(CustomerSupplier custSupp);
	public List<CustomerSupplier> getAllForDropDown();
}
