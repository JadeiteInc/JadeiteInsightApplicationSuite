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
public enum CommonConstants {

	REPORT_ID_SGDLYHIST ("SGDLYHIST"),
	
	
	LINE_NUM_1 	("1"),
	LINE_NUM_2 	("2"),
	LINE_NUM_3 	("3"),
	LINE_NUM_4 	("4"),
	
	HA_95703	("95703"),
	MO_83451	("83451"),
	TO_65052	("65052"),
	WI_05827	("05827"),
	CA_23069	("23069"),
	VA_93120	("93120"),
	
	OPENING_BALANCE_TXT ("OPENING BALANCE"),
	DAILY_BALANCE_TXT   ("DAILY BALANCE"),
	CLOSING_BALANCE_TXT	("CLOSING BALANCE");
	
	String value;
	
	private CommonConstants(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	
}
