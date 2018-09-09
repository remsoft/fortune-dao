package com.rem.fortune.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rem.fortune.dao.SupplierDao;
import com.rem.fortune.model.CustomerSupplier;

@Repository("SupplierDao")
public class SupplierDaoImpl extends FortuneDao implements SupplierDao{

	@Override
	public CustomerSupplier getSupplierById(String id) {
		return jdbcTemplate.queryForObject(DaoConstant.SELECT_CUST_SUPPLIER_BY_ID, new Object[] {id}, new SupplierRowMapper());
	}
	
	private static class SupplierRowMapper  implements RowMapper<CustomerSupplier> {
		@Override
		public CustomerSupplier mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerSupplier cs = new CustomerSupplier();
			cs.setId(rs.getString("id"));
			cs.setName(rs.getString("name"));
			cs.setEmail(rs.getString("email"));
			return cs;
		}		
	}

	@Override
	@Transactional
	public String createSupplier(CustomerSupplier custSupp) {
		custSupp.getAddress().setId(UUID.randomUUID().toString());		
		jdbcTemplate.update(DaoConstant.INSERT_ADDRESS, new Object[] {custSupp.getAddress().getId(),custSupp.getAddress().getState(),custSupp.getAddress().getCity(),
				custSupp.getAddress().getState(),custSupp.getAddress().getZip(),custSupp.getAddress().getCountry(),custSupp.getAddress().getAttention()});
		custSupp.setId(UUID.randomUUID().toString());
		jdbcTemplate.update(DaoConstant.INSERT_CUSTOMER_SUPPLIER, new Object[] {custSupp.getId(),custSupp.getName(),custSupp.getPhone(),custSupp.getEmail(),custSupp.getAddress().getId(),custSupp.getIsCustomer()});
		return null;
	}

}
