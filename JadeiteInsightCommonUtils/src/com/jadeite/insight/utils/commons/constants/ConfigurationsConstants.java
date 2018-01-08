/**
 * ECPS Health Center
 */
package com.jadeite.insight.utils.commons.constants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Sep 17, 2017
 * 
 *
 */
public enum ConfigurationsConstants {

	BASIC_CONFIG_DIR_NAME		("basic",						"The Basic directory containing basic cofiguration files for app.", true),	
	
	BASIC_CONFIG				("BASIC_CONFIG",				"Basic configurations that contains path to Config Directory and others etc.",true),
	QUERIES_CONFIG				("QUERIES_CONFIG",				"Basic configurations that contains path to Config Directory and others etc.",true),
	DATABASE_CONFIG				("DATABASE_CONFIG",				"Basic configurations that contains path to Config Directory and others etc.",true),
	INIT_COMMAND_QUEUE_CONFIG	("INIT_COMMAND_QUEUE_CONFIG",	"Basic configurations that contains path to Config Directory and others etc.",true),
	LOGIC_COMMAND_QUEUE_CONFIG	("LOGIC_COMMAND_QUEUE_CONFIG",	"Basic configurations that contains path to Config Directory and others etc.",true);
	
	String name;
	String purpose;
	boolean required;
	
	private ConfigurationsConstants(String name, String purpose, boolean available) {
		this.required = available;
		this.name = name;
		this.purpose = purpose;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

}
