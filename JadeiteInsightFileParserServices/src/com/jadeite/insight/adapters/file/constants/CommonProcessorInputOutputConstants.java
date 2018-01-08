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
public enum CommonProcessorInputOutputConstants {

	SOURCE_FILE_DETAILS				("SOURCE_FILE_DETAILS", "Source File Details"),
	PARSED_SGDLYHIST_DATA 		("NORMALIZED_SGDLYHIST_DATA", "Normalized data for the SGDLYHIST Report."),
	CANONICAL_SGDLYHIST_DATA		("TRANSFORMED_SGDLYHIST_DATA","");
	
	String key;
	String description;
	
	private CommonProcessorInputOutputConstants(String key, String desc) {
		this.key = key;
		this.description = desc;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
