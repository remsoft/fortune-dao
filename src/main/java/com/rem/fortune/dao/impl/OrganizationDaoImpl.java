package com.rem.fortune.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rem.fortune.dao.OrganizationDao;
import com.rem.fortune.model.Address;
import com.rem.fortune.model.CustomerSupplier;
import com.rem.fortune.model.Organization;

@Repository("OrganizationDao")
public class OrganizationDaoImpl extends FortuneDao implements OrganizationDao {

	@Override
	public Organization getById(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(DaoConstant.SELECT_ORGANIZATION_BY_ID, new Object[] {id}, new OrganizationRowMapper());
	}

	private static class OrganizationRowMapper  implements RowMapper<Organization> {
		@Override
		public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
			Organization og = new Organization();
			og.setId(rs.getInt("id"));
			og.setDisplayName(rs.getString("display_name"));
			og.setLogo(rs.getString("logo"));
			og.setRegistrationNumber(rs.getString("registration_number"));
			og.setDescription(rs.getString("description"));
			Address address = new Address();
				address.setStreet(rs.getString("street"));
				address.setState(rs.getString("state"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setZip(rs.getString("zip"));
			og.setAddress(address);
			return og;
		}		
	}
	
	@Override
	public int create(Organization organization) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Organization> getAll() {
		// TODO Auto-generated method stub
		List<Organization> organizationList = new ArrayList<Organization>();			
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_ORGANIZATION);
		for(Map<String, Object> map : resultSet) {
			Organization og = new Organization();
			og.setId((int) map.get("id"));
			og.setDisplayName((String) map.get("display_name"));
			og.setLogo((String) map.get("logo"));
			og.setRegistrationNumber((String) map.get("registration_number"));
			og.setDescription((String) map.get("description"));
			Address address = new Address();
				address.setStreet((String) map.get("street"));
				address.setState((String) map.get("state"));
				address.setCity((String) map.get("city"));
				address.setState((String) map.get("state"));
				address.setZip((String) map.get("zip"));
			og.setAddress(address);
			organizationList.add(og);
		}
		return organizationList;
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		 return jdbcTemplate.update(DaoConstant.DELETE_ORGANIZATION_BY_ID, new Object[] {id});
	}
	

}
