package com.rem.fortune.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rem.fortune.dao.SupplierDao;
import com.rem.fortune.model.Address;
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

	@Override
	public List<CustomerSupplier> getCustomerSupplierAll(int isCustomer) {
		List<CustomerSupplier> results = new ArrayList<CustomerSupplier>();
		PreparedStatement ps;
		try {
			ps = getJdbcTemplate().getDataSource().getConnection().prepareStatement(DaoConstant.SELECT_CUST_SUPPLIER);
			ps.setInt(1, isCustomer);
			ResultSet rs = ps.executeQuery();
			while ( rs.next() )
		    {
				CustomerSupplier cs = new CustomerSupplier();
				cs.setId(rs.getInt("id"));
				cs.setEmail(rs.getString("email"));
				cs.setPhone(rs.getString("phone"));
				cs.setName(rs.getString("name"));
				cs.setIsCustomer(0);
				Address address = new Address();
				address.setCity(rs.getString("city"));
				address.setStreet(rs.getString("street"));
				address.setState(rs.getString("state"));
				cs.setAddress(address);
				results.add(cs);				
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		    
		return results;
	}

}
