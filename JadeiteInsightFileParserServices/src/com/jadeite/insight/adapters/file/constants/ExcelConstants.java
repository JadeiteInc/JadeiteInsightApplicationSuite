/**
 * ECPS Health Center
 */
package com.bns.ecps.hc.finmon.constants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 24, 2017
 * 
 *
 */
public enum ExcelConstants {

	TABLE_STYLE_LIGHT_4 	("TableStyleLight4"),
	TABLE_STYLE_LIGHT_11 	("TableStyleLight11"),
	TABLE_STYLE_LIGHT_18 	("TableStyleLight18"),
	
	TABLE_STYLE_MEDIUM_4 	("TableStyleMedium4"),
	TABLE_STYLE_MEDIUM_11 	("TableStyleMedium11"),
	TABLE_STYLE_MEDIUM_18 	("TableStyleMedium18"),
	TABLE_STYLE_MEDIUM_25 	("TableStyleMedium25");
	
	
	private ExcelConstants(String key) {
		this.key = key;
	}
	
	String key;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	
}
