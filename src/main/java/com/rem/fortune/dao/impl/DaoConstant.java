package com.rem.fortune.dao.impl;

public class DaoConstant {
	/* Query for cust_supp tbale */
	public static String SELECT_CUST_SUPPLIER_BY_ID="SELECT * FROM cust_supp where id=?";
	public static String INSERT_ADDRESS="INSERT INTO address (street,city,state,zip,country,attention,created_by,created_dt,modified_by,modified_dt)"
			+ " VALUES (?,?,?,?,?,?,?,sysdate(),?,sysdate())";
	public static String INSERT_CUSTOMER_SUPPLIER="INSERT INTO cust_supp(name,phone,email,address_id,is_customer,created_by,created_dt,modified_by,modified_dt)"
			+ " VALUES (?,?,?,?,?,?,sysdate(),?,sysdate())";
	public static String SELECT_CUST_SUPPLIER="SELECT cs.id,cs.name,cs.email,cs.address_id,cs.is_customer,cs.phone,ad.street,ad.city,ad.state,ad.zip,ad.country,ad.attention FROM fortuna.cust_supp cs inner join address ad "
			+ "on cs.address_id= ad.id where cs.is_customer=?";
	public static String DELETE_CUST_SUPPLIER_BY_ID="DELETE FROM cust_supp WHERE id=?";
	public static String UPDATE_CUSTOMER_SUPPLIER="UPDATE cust_supp set name=?,phone=?,email=?,modified_by=?,modified_dt=sysdate() where id=?";
	public static String UPDATE_ADDRESS="UPDATE address set street=?,city=?,state=?,zip=?,country=?,attention=?,modified_by=?,modified_dt=sysdate() where id=?";
	/* Query for coa tbale */
	public static String SELECT_COA="SELECT id,l1,l2,l3,l4,l5,coa_code,name,description,favorite,created_by,created_dt,modified_by,modified_dt FROM coa";
	
}
