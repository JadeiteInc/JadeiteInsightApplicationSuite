/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.constants;

/**
 * @author Jay Mukherjee
 * 
 * @date Nov 9, 2017
 * 
 *
 */
public enum SGDLYHISTReportDataFormatConstants {

	REPORT_DATA;

	public enum ACCOUNT_NUMBER_LINE {

		ACCOUNT_NUMBER("ACCOUNT_NUMBER", 1, 8, null, null, ""), ACCOUNT_NAME("ACCOUNT_NAME", 9, 124, null, null, "");

		String key;
		int startPosition;
		int length;
		String dbTable;
		String dbColumn;
		String defaultValue;

		private ACCOUNT_NUMBER_LINE(String key, int startPosition, int length, String dbTable, String dbColumn,
				String defaultValue) {
			this.key = key;
			this.startPosition = startPosition;
			this.length = length;
			this.dbTable = dbTable;
			this.dbColumn = dbColumn;
			this.defaultValue = defaultValue;
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

	public enum OPENING_BALANCE_LINE {

		OPENING_BALANCE("OPENING_BALANCE", 95, 38, null, null, "");

		String key;
		int startPosition;
		int length;
		String dbTable;
		String dbColumn;
		String defaultValue;

		private OPENING_BALANCE_LINE(String key, int startPosition, int length, String dbTable, String dbColumn,
				String defaultValue) {
			this.key = key;
			this.startPosition = startPosition;
			this.length = length;
			this.dbTable = dbTable;
			this.dbColumn = dbColumn;
			this.defaultValue = defaultValue;
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

	public enum REPORT_DATA_LINE {

		TRANS_DATE 		("TRANS_DATE", 1, 13, null, null, ""),
		MNEMONIC		("MNEMONIC", 14, 14, null, null, ""),
		AMOUNT			("AMOUNT", 28, 21, null, null, ""),
		NARRATIVE		("NARRATIVE", 49, 11, null, null, ""),
		LOC_SOURCE		("LOC_SOURCE", 60, 73, null, null, "");

		String key;
		int startPosition;
		int length;
		String dbTable;
		String dbColumn;
		String defaultValue;

		private REPORT_DATA_LINE(String key, int startPosition, int length, String dbTable, String dbColumn, String defaultValue) {
			this.key = key;
			this.startPosition = startPosition;
			this.length = length;
			this.dbTable = dbTable;
			this.dbColumn = dbColumn;
			this.defaultValue = defaultValue;
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

	public enum DAILY_BALANCE_LINE {

		DAILY_BALANCE("DAILY_BALANCE", 95, 38, null, null, "");

		String key;
		int startPosition;
		int length;
		String dbTable;
		String dbColumn;
		String defaultValue;

		private DAILY_BALANCE_LINE(String key, int startPosition, int length, String dbTable, String dbColumn, String defaultValue) {
			this.key = key;
			this.startPosition = startPosition;
			this.length = length;
			this.dbTable = dbTable;
			this.dbColumn = dbColumn;
			this.defaultValue = defaultValue;
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

	public enum CLOSING_BALANCE_LINE {

		CLOSING_BALANCE("CLOSING_BALANCE", 95, 38, null, null, "");

		String key;
		int startPosition;
		int length;
		String dbTable;
		String dbColumn;
		String defaultValue;

		private CLOSING_BALANCE_LINE(String key, int startPosition, int length, String dbTable, String dbColumn, String defaultValue) {
			this.key = key;
			this.startPosition = startPosition;
			this.length = length;
			this.dbTable = dbTable;
			this.dbColumn = dbColumn;
			this.defaultValue = defaultValue;
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

}
