package com.rem.fortune.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rem.fortune.dao.DivisionDao;
import com.rem.fortune.model.DropDownModel;

@Repository("DivisionDao")
public class DivisionDaoImpl extends FortuneDao implements DivisionDao{

	@Override
	public List<DropDownModel> getAllForDropDown() {
		List<DropDownModel> divisions = new ArrayList<DropDownModel>();
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_DIVISION_DROP_DOWN);
		for(Map<String,Object> map :resultSet) {
			DropDownModel dv = new DropDownModel();
			dv.setId((int) map.get("id"));
			dv.setName((String) map.get("name"));
			divisions.add(dv);
		}
		return divisions;
	}

}
