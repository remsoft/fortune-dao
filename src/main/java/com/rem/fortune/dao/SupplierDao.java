package com.rem.fortune.dao;

import com.rem.fortune.model.CustomerSupplier;

public interface SupplierDao {
	public CustomerSupplier getSupplierById(String id);
	public int createSupplier(CustomerSupplier custSupp);
}
