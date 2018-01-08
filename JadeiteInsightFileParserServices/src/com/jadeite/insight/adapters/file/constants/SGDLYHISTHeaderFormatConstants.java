/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.constants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 9, 2017
 * 
 *
 */
public enum SGDLYHISTHeaderFormatConstants {

	HEADER;
	
	public enum FIRST_LINE {
		ROUTING_TRANSIT 		("ROUTING_TRANSIT",	1, 5, null, null, "00000", true),
		PRINTED_DATE    		("PRINTED_DATE",108, 20, null, null, "", false);
		
		String key;
		int startPosition;
		int length;
		String dbTable;
		String dbColumn;
		String defaultValue;
		boolean isKey;
		
		private FIRST_LINE(String key,int startPosition, int length, String dbTable, String dbColumn, String defaultValue, boolean isKey) {
			this.key 			= key;
			this.startPosition 	= startPosition;
			this.length 		= length;
			this.dbTable 		= dbTable;
			this.dbColumn 		= dbColumn;
			this.defaultValue 	= defaultValue;
			this.isKey			= isKey;
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

		/**
		 * @return the isKey
		 */
		public boolean isKey() {
			return isKey;
		}

				
	}
	
	public enum SECOND_LINE {
		ROUTING_TRANSIT_ADDRESS		("ROUTING_TRANSIT_ADDRESS",1, 40, null, null, "", true),
		REPORT_ID					("REPORT_ID",108, 20, null, null, "", true),
		PAGE_NUMBER					("PAGE_NUMBER",128, 4, null, null, "", false);
		
		String key;
		int startPosition;
		int length;
		String dbTable;
		String dbColumn;
		String defaultValue;
		boolean isKey;
		
		private SECOND_LINE(String key,int startPosition, int length, String dbTable, String dbColumn, String defaultValue, boolean isKey) {
			this.key 			= key;
			this.startPosition 	= startPosition;
			this.length 		= length;
			this.dbTable 		= dbTable;
			this.dbColumn 		= dbColumn;
			this.defaultValue 	= defaultValue;
			this.isKey			= isKey;
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

		/**
		 * @return the isKey
		 */
		public boolean isKey() {
			return isKey;
		}
				
	}
	
	public enum THIRD_LINE {
		ROUTING_TRANSIT_REGION		("ROUTING_TRANSIT_REGION",1, 40, null, null, "", true),
		CURRENCY					("CURRENCY",70, 3, null, null, "", true),
		REPORT_MONTH				("REPORT_MONTH",108, 4, null, null, "", true);
		
		String key;
		int startPosition;
		int length;
		String dbTable;
		String dbColumn;
		String defaultValue;
boolean isKey;
		
		private THIRD_LINE(String key,int startPosition, int length, String dbTable, String dbColumn, String defaultValue, boolean isKey) {
			this.key 			= key;
			this.startPosition 	= startPosition;
			this.length 		= length;
			this.dbTable 		= dbTable;
			this.dbColumn 		= dbColumn;
			this.defaultValue 	= defaultValue;
			this.isKey			= isKey;
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

		/**
		 * @return the isKey
		 */
		public boolean isKey() {
			return isKey;
		}
		
	}
}
