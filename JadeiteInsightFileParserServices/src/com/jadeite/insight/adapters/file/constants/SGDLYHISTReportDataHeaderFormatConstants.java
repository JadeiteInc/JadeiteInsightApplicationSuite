/**
 * ECPS Health Center
 */
package com.bns.ecps.hc.finmon.constants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 9, 2017
 * 
 *
 */
public enum SGDLYHISTReportDataHeaderFormatConstants {

	ACCOUNT_HEADER 			("ACCOUNT_HEADER",	1, 9, null, null, "ACCOUNT"),
	TRAN_DATE_HEADER   		("PRINTED_DATE", 10, 7, null, null, "TRAN-DATE"),
	MNEMONIC_HEADER			("MNEMONIC_HEADER", 17, 17, null, null, "MNEMONIC"),
	TRAN_AMOUNT_HEADER		("TRAN_AMOUNT_HEADER", 34, 15, null, null, "TRANSACTION-AMT"),
	NARRATIVE_HEADER		("NARRATIVE_HEADER", 49, 11, null, null, "NARRATIVE"),
	LOC_SOURCE_HEADER		("LOC_SOURCE_HEADER", 60, 8, null, null, "LOCATOR-SOURCE");
	
	
	String key;
	int startPosition;
	int length;
	String dbTable;
	String dbColumn;
	String defaultValue;
	
	private SGDLYHISTReportDataHeaderFormatConstants(String key,int startPosition, int length, String dbTable, String dbColumn, String defaultValue) {
		this.key 			= key;
		this.startPosition 	= startPosition;
		this.length 		= length;
		this.dbTable 		= dbTable;
		this.dbColumn 		= dbColumn;
		this.defaultValue 	= defaultValue;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the startPosition
	 */
	public int getStartPosition() {
		return startPosition;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return the dbTable
	 */
	public String getDbTable() {
		return dbTable;
	}

	/**
	 * @return the dbColumn
	 */
	public String getDbColumn() {
		return dbColumn;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	
	
}
