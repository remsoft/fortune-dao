package com.rem.fortune.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.rem.fortune.dao.CoaDao;
import com.rem.fortune.model.Coa;

@Repository("CoaDao")
public class CoaDaoImpl extends FortuneDao implements CoaDao{

	@Override
	public List<Coa> getAll(int accountType,String coaCd) {
		List<Coa> coas = new ArrayList<Coa>();
		List<Map<String, Object>> resultSet = null;
		if(accountType!=0) {
			resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_COA_BY_ACCOUNT_TYPE, new Object[] {accountType,"%"+coaCd+"%"});			
		}else {
			resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_COA_ALL, new Object[]{"%"+coaCd+"%"});
		}
		for(Map<String,Object> map :resultSet) {
			Coa coa = new Coa();
			coa.setId((int) map.get("id"));
			coa.setL1AccountType((int) map.get("l1"));
			coa.setL2Branch((int) map.get("l2"));
			coa.setL3CustSupp((int) map.get("l3"));
			coa.setL4Division((int) map.get("l4"));
			coa.setL5Custom((int) map.get("l5"));
			coa.setCoaCd((String) map.get("coa_code"));
			coa.setName((String) map.get("coa_name"));			
			coa.setDescription((String) map.get("description"));			
			coa.setFavorite((String) map.get("favorite"));			
			coa.setL1AccountTypName((String) map.get("acc_typ_name"));
			coa.setL1AccountTypGrp((String) map.get("acc_grp_name"));
			coas.add(coa);			
		}
		return coas;
	}

	@Override
	public int create(Coa coa) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int size=jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	            PreparedStatement ps =(PreparedStatement) connection.prepareStatement(DaoConstant.INSERT_COA, Statement.RETURN_GENERATED_KEYS);
	            ps.setInt(1, coa.getL1AccountType());
	            ps.setInt(2, coa.getL2Branch());
	            ps.setInt(3, coa.getL3CustSupp());
	            ps.setInt(4, coa.getL4Division());
	            // temporarily default to 1 
	            // ps.setInt(5, coa.getL5Custom());
	            ps.setInt(5, 1);
	            ps.setInt(6, coa.getTax());
	            ps.setString(7, coa.getCoaCd());
	            ps.setString(8, coa.getName());
	            ps.setString(9, coa.getDescription());
	            ps.setString(10, coa.getFavorite());
	            ps.setString(11,"Rizky");
	            ps.setString(12,"Rizky");
				return ps;
			}
			
		},keyHolder);
		return size;
	}

	@Override
	public int update(Coa coa) {
		int size = jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(DaoConstant.UPDATE_COA);
			       ps.setInt(1, coa.getL1AccountType());
		            ps.setInt(2, coa.getL2Branch());
		            ps.setInt(3, coa.getL3CustSupp());
		            ps.setInt(4, coa.getL4Division());
		            ps.setInt(5, coa.getL5Custom());
		            ps.setString(6, coa.getCoaCd());
		            ps.setString(7, coa.getName());
		            ps.setString(8, coa.getDescription());
		            ps.setString(9, coa.getFavorite());
		            ps.setString(10,"Rizky");
		            ps.setInt(11, coa.getId());
				return ps;
			}	
		});
		return size;
	}

	@Override
	public int delete(int id) {
		int size = 0;
		try{
			size = jdbcTemplate.update(DaoConstant.DELETE_COA,new Object[]{id});
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		return size;
	}

}
