package com.rem.fortune.dao.impl;

public class DaoConstant {
	public static String SELECT_CUST_SUPPLIER_BY_ID="SELECT * FROM cust_supp where id=?";
	public static String INSERT_ADDRESS="INSERT INTO address (street,city,state,zip,country,attention,created_by,created_dt,modified_by,modified_dt)"
			+ " VALUES (?,?,?,?,?,?,?,sysdate(),?,sysdate())";
	public static String INSERT_CUSTOMER_SUPPLIER="INSERT INTO cust_supp(name,phone,email,address_id,is_customer,created_by,created_dt,modified_by,modified_dt)"
			+ " VALUES (?,?,?,?,?,?,sysdate(),?,sysdate())";
	public static String SELECT_CUST_SUPPLIER="SELECT cs.id,cs.name,cs.email,cs.phone,ad.street,ad.city,ad.state FROM fortuna.cust_supp cs inner join address ad\r\n" + 
			"on cs.address_id= ad.id where cs.is_customer=?";
	
	
}
