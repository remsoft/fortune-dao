package com.rem.fortune.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rem.fortune.dao.BranchDao;
import com.rem.fortune.model.Branch;

@Repository("BranchDao")
public class BranchDaoImpl extends FortuneDao implements BranchDao {

	@Override
	public List<Branch> getAllForDropDown() {
		List<Branch> branches = new ArrayList<Branch>();
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_BRANCH_DROP_DOWN);
		for(Map<String,Object> map :resultSet) {
			Branch br = new Branch();
			br.setId((int) map.get("id"));
			br.setName((String) map.get("name"));
			branches.add(br);
		}
		return branches;

	}

}
