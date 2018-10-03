package com.rem.fortune.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rem.fortune.dao.TaxDao;
import com.rem.fortune.model.DropDownModel;

@Repository
public class TaxDaoImpl extends FortuneDao implements TaxDao{

	@Override
	public List<DropDownModel> getAllForDropDown() {
		List<DropDownModel> taxs = new ArrayList<DropDownModel>();
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_TAX_DROP_DOWN);
		for(Map<String,Object> map :resultSet) {
			DropDownModel dv = new DropDownModel();
			dv.setId((int) map.get("id"));
			dv.setName((String) map.get("name"));
			taxs.add(dv);
		}
		return taxs;
	}

}
