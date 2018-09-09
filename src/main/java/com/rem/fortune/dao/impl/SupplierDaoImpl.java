package com.rem.fortune.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
			cs.setId(rs.getInt("id"));
			cs.setName(rs.getString("name"));
			cs.setEmail(rs.getString("email"));
			return cs;
		}		
	}

	@Override
	@Transactional
	public int createSupplier(CustomerSupplier custSupp) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 
		int save = getJdbcTemplate().update(new PreparedStatementCreator(){
		    public java.sql.PreparedStatement createPreparedStatement(
		        java.sql.Connection connection) throws SQLException {
		            PreparedStatement ps =(PreparedStatement) connection.prepareStatement(DaoConstant.INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);
		            ps.setString(1, custSupp.getAddress().getStreet());
		            ps.setString(2, custSupp.getAddress().getCity());
		            ps.setString(3, custSupp.getAddress().getState());
		            ps.setString(4, custSupp.getAddress().getZip());
		            ps.setString(5, custSupp.getAddress().getCountry());
		            ps.setString(6, custSupp.getAddress().getAttention());
		            ps.setString(7, "Riz");
		            ps.setString(8, "Riz");
		            return ps;
		        }
		    },keyHolder);
			
			save = getJdbcTemplate().update(new PreparedStatementCreator(){
			    public java.sql.PreparedStatement createPreparedStatement(
			        java.sql.Connection connection) throws SQLException {
			            PreparedStatement ps =(PreparedStatement) connection.prepareStatement(DaoConstant.INSERT_CUSTOMER_SUPPLIER, Statement.RETURN_GENERATED_KEYS);
			            ps.setString(1, custSupp.getName());
			            ps.setString(2, custSupp.getPhone());
			            ps.setString(3, custSupp.getEmail());
			            ps.setInt(4, keyHolder.getKey().intValue());
			            ps.setInt(5, custSupp.getIsCustomer());
			            ps.setString(6, "Riz");
			            ps.setString(7, "Riz");

			            return ps;
			        }
			    },keyHolder);
		
		return save;
	}

}
