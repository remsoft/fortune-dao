package com.rem.fortune.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rem.fortune.dao.DivisionDao;
import com.rem.fortune.model.Division;


public class DivisionDaoImpl extends FortuneDao implements DivisionDao{

	@Override
	public List<Division> getAllForDropDown() {
		List<Division> divisions = new ArrayList<Division>();
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_DIVISION_DROP_DOWN);
		for(Map<String,Object> map :resultSet) {
			Division dv = new Division();
			dv.setId((int) map.get("id"));
			dv.setName((String) map.get("name"));
			divisions.add(dv);
		}
		return divisions;
	}

}
