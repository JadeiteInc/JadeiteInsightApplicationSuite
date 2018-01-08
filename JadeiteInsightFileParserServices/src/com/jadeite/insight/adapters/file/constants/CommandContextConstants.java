/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.constants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 20, 2017
 * 
 *
 */
public enum CommandContextConstants {

	/*
	 * Enum for - SG Daily History File information/Path 
	 */
	SG_DAILY_HISTORY_FILE_PATH 		("sgDailyHistoryFilePath"),
	
	/*
	 * Enum for - SG DAILY HISTORY File Parsed Information.
	 */
	SG_DAILY_HISTORY_RAW_DATA		("sgDailyHistoryRawData"),
	
	/*
	 * Enum for - Financial Monitor Config Directories
	 */
	FIN_MONITOR_CONFIG_DIR_PATH		("FIN_MONITOR_CONFIG_DIR_PATH"),
	
	/*
	 * Enum for - Canonical PARSED DATA for SG DAILY HISTORY
	 */
	SG_DAILY_HISTORY_CANONICAL_DATA ("sgDailyHistoryCanonicalData"),
	
	FIN_MONITOR_SUMMARY_EXCEL_PATH	("FIN_MONITOR_SUMMARY_EXCEL_PATH"),
	
	SG_DAILY_HISTORY_CANONICAL_CIDC_DATA ("sgDailyHistoryCanonicalCIDCData");
	
	
	
	String key;
	
	private CommandContextConstants(String key) {
		this.key = key;
	}
	
	
}
