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
	
	// QUERY CONSTANT ORGANIZATION
	public static String INSERT_ORGANIZATION= "INSERT INTO organization (display_name, logo, registration_number, description, address_id, created_by, created_dt, modified_by, modified_dt)"
			+" VALUES(?,?,?,?, ?,?,sysdate(),?,sysdate())";
	public static String DELETE_ORGANIZATION_BY_ID="DELETE FROM organization WHERE id=?";
	public static String SELECT_ORGANIZATION="SELECT og.id, og.display_name, og.logo, og.registration_number, og.description,  ad.street, ad.city, ad.state, ad.zip"
			+" FROM organization og JOIN address ad ON og.address_id = ad.id;";
	public static String SELECT_ORGANIZATION_BY_ID="SELECT og.id, og.display_name, og.logo, og.registration_number, og.description,  ad.street, ad.city, ad.state, ad.zip"
			+" FROM organization og JOIN address ad ON og.address_id = ad.id where og.id=?";
	public static String UPDATE_ORGANIZATION_BY_ID="UPDATE fortuna.organization" 
			+ " SET display_name=?, logo=?, registration_number=?, description=?, address_id=?, modified_by=?, modified_dt=sysdate()" + 
			"WHERE id=?";

	public static String UPDATE_CUSTOMER_SUPPLIER="UPDATE cust_supp set name=?,phone=?,email=?,modified_by=?,modified_dt=sysdate() where id=?";
	public static String UPDATE_ADDRESS="UPDATE address set street=?,city=?,state=?,zip=?,country=?,attention=?,modified_by=?,modified_dt=sysdate() where id=?";
	
	/* Query for coa table */
	public static String SELECT_COA_BY_ACCOUNT_TYPE="SELECT c.id,c.l1,c.l2,c.l3,c.l4,c.l5,c.coa_code,c.name as coa_name,c.description,c.favorite,ac.name as acc_typ_name,acc.name as acc_grp_name " + 
			"FROM coa c inner join account_type ac " + 
			"on c.l1 = ac.id " + 
			"inner join account_classification acc " + 
			"on ac.classification_id = acc.id "+
			"where acc.id = ?";
	
	public static String SELECT_COA_ALL="SELECT c.id,c.l1,c.l2,c.l3,c.l4,c.l5,c.coa_code,c.name as coa_name,c.description,c.favorite,ac.name as acc_typ_name,acc.name as acc_grp_name " + 
			"FROM coa c inner join account_type ac " + 
			"on c.l1 = ac.id " + 
			"inner join account_classification acc " + 
			"on ac.classification_id = acc.id ";

	public static String SELECT_ACC_TYPE_DROP_DOWN="SELECT id, name FROM account_type";
	public static String SELECT_BRANCH_DROP_DOWN="SELECT id, name FROM branch";
	public static String SELECT_CUST_SUPP_DROP_DOWN="SELECT id, name FROM cust_supp";
	public static String SELECT_DIVISION_DROP_DOWN="SELECT id, name FROM division";
	public static String SELECT_COA_CUSTOM_LEVELN1_DROP_DOWN="SELECT id,name FROM custom_level";
	
}
