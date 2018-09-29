package com.rem.fortune.dao;

import java.util.List;

import com.rem.fortune.model.Coa;

public interface CoaDao {
	public List<Coa> getAll(int accountType,String coaCd);
	public int create(Coa coa);
	public int update(Coa coa);
	public int delete(int id);

}
