package com.rem.fortune.dao;

import java.util.List;

import com.rem.fortune.model.Organization;

public interface OrganizationDao {
	public Organization getById(int id);
	public int create(Organization organization);
	public List<Organization> getAll();
	public int deleteById(int id);
}
