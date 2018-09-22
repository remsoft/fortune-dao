package com.rem.fortune.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rem.fortune.dao.CoaDao;
import com.rem.fortune.model.Coa;

@Repository("CoaDao")
public class CoaDaoImpl extends FortuneDao implements CoaDao{

	@Override
	public List<Coa> getAll() {
		List coas = new ArrayList<Coa>();
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_COA);
		for(Map<String,Object> map :resultSet) {
			Coa coa = new Coa();
			coa.setId((int) map.get("id"));
			coa.setL1AccountType((int) map.get("l1"));
			coa.setL2Branch((int) map.get("l2"));
			coa.setL3CustSupp((int) map.get("l3"));
			coa.setL4Division((int) map.get("l4"));
			coa.setL5Custom((int) map.get("l5"));
			coa.setCoaCd((String) map.get("coa_code"));
			coa.setName((String) map.get("name"));			
			coa.setDescription((String) map.get("description"));			
			coa.setFavorite((String) map.get("favorite"));			
			coas.add(coa);			
		}
		return coas;
	}

}
