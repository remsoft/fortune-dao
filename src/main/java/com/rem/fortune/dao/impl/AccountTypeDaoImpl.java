package com.rem.fortune.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rem.fortune.dao.AccountTypeDao;
import com.rem.fortune.model.DropDownModel;

@Repository("AccountTypeDao")
public class AccountTypeDaoImpl extends FortuneDao implements AccountTypeDao{

	@Override
	public List<DropDownModel> getAllForDropDown() {
		List<DropDownModel> accountTypes = new ArrayList<DropDownModel>();
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_ACC_TYPE_DROP_DOWN);
		for(Map<String,Object> map :resultSet) {
			DropDownModel ac = new DropDownModel();
			ac.setId((int) map.get("id"));
			ac.setName((String) map.get("name"));
			accountTypes.add(ac);
		}
		return accountTypes;
	}

}
