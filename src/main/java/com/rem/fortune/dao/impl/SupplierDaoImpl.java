package com.rem.fortune.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rem.fortune.dao.SupplierDao;
import com.rem.fortune.model.CustomerSupplier;

@Repository("SupplierDao")
public class SupplierDaoImpl extends FortuneDao implements SupplierDao{

	@Override
	public CustomerSupplier getCustomerSupportById(String id) {
		System.out.println("----------------> ID:"+id);
		return jdbcTemplate.queryForObject(DaoConstant.SELECT_CUST_SUPPORT_BY_ID, new Object[] {id}, new CustomerSupportRowMapper());
	}
	
	private static class CustomerSupportRowMapper  implements RowMapper<CustomerSupplier> {
		@Override
		public CustomerSupplier mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerSupplier cs = new CustomerSupplier();
			cs.setId(rs.getString("id"));
			cs.setName(rs.getString("name"));
			cs.setEmail(rs.getString("email"));
			return cs;
		}		
	}

}
