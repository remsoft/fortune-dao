package com.rem.fortune.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public CustomerSupplier getById(int id){
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
	public int create(CustomerSupplier custSupp){
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
	public List<CustomerSupplier> getAll(int isCustomer){
		List<CustomerSupplier> results = new ArrayList<CustomerSupplier>();
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_CUST_SUPPLIER, new Object[] {0});
		for(Map<String,Object> map :resultSet) {
			CustomerSupplier cs = new CustomerSupplier();
			cs.setId((int) map.get("id"));
			cs.setEmail((String) map.get("email"));
			cs.setPhone((String)map.get("phone"));
			cs.setName((String)map.get("name"));
			cs.setIsCustomer(0);
			Address address = new Address();
			address.setId((int) map.get("address_id"));
			address.setCity((String)map.get("city"));
			address.setStreet((String)map.get("street"));
			address.setState((String)map.get("state"));
			address.setZip((String) map.get("zip"));
			address.setCountry((String) map.get("country"));
			address.setAttention((String) map.get("attention"));
			cs.setAddress(address);
			results.add(cs);		
		}
		    
		return results;
	}

	@Override
	public int deleteById(int id){
		return jdbcTemplate.update(DaoConstant.DELETE_CUST_SUPPLIER_BY_ID,new Object[] {id});
	}

	@Override
	public int updateById(CustomerSupplier custSupp){
		int save = getJdbcTemplate().update(new PreparedStatementCreator(){
			    public java.sql.PreparedStatement createPreparedStatement(
			        java.sql.Connection connection) throws SQLException {
			            PreparedStatement ps =(PreparedStatement) connection.prepareStatement(DaoConstant.UPDATE_CUSTOMER_SUPPLIER);
			            ps.setString(1, custSupp.getName());
			            ps.setString(2, custSupp.getPhone());
			            ps.setString(3, custSupp.getEmail());
			            ps.setString(4, "Riz");
			            ps.setInt(5, custSupp.getId());
			            return ps;
			        }
			    });		
			return save;
	}

	@Override
	public int updateAddressById(CustomerSupplier custSupp) {
		Address address = custSupp.getAddress();
		if(address.getId()!=0) {				
			int save = getJdbcTemplate().update(new PreparedStatementCreator(){
			    public java.sql.PreparedStatement createPreparedStatement(
			        java.sql.Connection connection) throws SQLException {
			            PreparedStatement ps =(PreparedStatement) connection.prepareStatement(DaoConstant.UPDATE_ADDRESS);
			            ps.setString(1, address.getStreet());
			            ps.setString(2, address.getCity());
			            ps.setString(3, address.getState());
			            ps.setString(4, address.getZip());
			            ps.setString(5, address.getCountry());
			            ps.setString(6, address.getAttention());
			            ps.setString(7, "Riz");
			            ps.setInt(8, address.getId());
			            return ps;
			        }
			    });
			return save;
			
		
		}else {
			KeyHolder keyHolder = new GeneratedKeyHolder();			 
			int save = getJdbcTemplate().update(new PreparedStatementCreator(){
			    public java.sql.PreparedStatement createPreparedStatement(
			        java.sql.Connection connection) throws SQLException {
			            PreparedStatement ps =(PreparedStatement) connection.prepareStatement(DaoConstant.INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);
			            ps.setString(1, address.getStreet());
			            ps.setString(2, address.getCity());
			            ps.setString(3, address.getState());
			            ps.setString(4, address.getZip());
			            ps.setString(5, address.getCountry());
			            ps.setString(6, address.getAttention());
			            ps.setString(7, "Riz");
			            ps.setString(8, "Riz");
			            return ps;
			        }
			    },keyHolder);
				return save;
		}
	}
	

}
