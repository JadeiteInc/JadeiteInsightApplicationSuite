/**
 * ECPS Health Center
 */
package com.bns.ecps.hc.finmon.constants;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Jay Mukherjee
 * 
 * @date Nov 9, 2017
 * 
 *
 */
public enum CanonicalSGDLYHISTReportDataConstants {

	ROUTING_TRANSIT			("ROUTING_TRANSIT", 		String.class, 		5, 		null, null, "00000", 	true), 
	PRINTED_DATE			("PRINTED_DATE", 			Timestamp.class, 	20, 	null, null,	"", 		false), 
	ROUTING_TRANSIT_ADDRESS	("ROUTING_TRANSIT_ADDRESS", String.class, 		40, 	null, null, "", 		true), 
	REPORT_ID				("REPORT_ID", 				String.class, 		20, 	null, null, "", 		false), 
	PAGE_NUMBER				("PAGE_NUMBER", 			Integer.class, 		4, 		null, null, "", 		false), 
	ROUTING_TRANSIT_REGION	("ROUTING_TRANSIT_REGION", 	String.class, 		40, 	null, null, "", 		true), 
	CURRENCY				("CURRENCY", 				String.class, 		3, 		null, null,	"", 		true), 
	REPORT_MONTH			("REPORT_MONTH", 			String.class, 		4, 		null, null, "", 		true),
	ACCOUNT_NUMBER			("ACCOUNT_NUMBER", 			String.class, 		8, 		null, null, "", 		false), 
	ACCOUNT_NAME			("ACCOUNT_NAME", 			String.class, 		124, 	null, null,	"", 		false), 
	OPENING_BALANCE			("OPENING_BALANCE", 		Double.class, 		38, 	null, null, "", 		false), 
	TRANS_DATE				("TRANS_DATE", 				Date.class, 		13, 	null, null,	"", 		false), 
	MNEMONIC				("MNEMONIC", 				String.class, 		14, 	null, null, "", 		false), 
	AMOUNT					("AMOUNT", 					Double.class, 		21, 	null, null,	"", 		false), 
	NARRATIVE				("NARRATIVE", 				String.class, 		11, 	null, null, "", 		false), 
	LOC_SOURCE				("LOC_SOURCE", 				String.class, 		73, 	null, null, "", 		false), 
	DAILY_BALANCE			("DAILY_BALANCE", 			Double.class, 		38, 	null, null, "", 		false), 
	CLOSING_BALANCE			("CLOSING_BALANCE", 		Double.class, 		38, 	null, null, "", 		false);

	String key;
	Class<?> datatype;
	int length;
	String dbTable;
	String dbColumn;
	String defaultValue;
	boolean isKey;

	private CanonicalSGDLYHISTReportDataConstants(String key, Class<?> datatype, int length, String dbTable, String dbColumn, String defaultValue, boolean isKey) {
		this.key 			= key;
		this.datatype 		= datatype;
		this.length 		= length;
		this.dbTable 		= dbTable;
		this.dbColumn 		= dbColumn;
		this.defaultValue 	= defaultValue;
		this.isKey			= isKey;
	}

	public String getKey() {
		return key;
	}

	public Class<?> getDatatype() {
		return datatype;
	}

	public int getLength() {
		return length;
	}

	public String getDbTable() {
		return dbTable;
	}

	public String getDbColumn() {
		return dbColumn;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public boolean isKey() {
		return isKey;
	}

}
