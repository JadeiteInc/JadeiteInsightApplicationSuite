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
public enum BasicConfigConstants {

	BASIC_APP_NAME 				("app.name",""),
	BASIC_APP_SERVER 			("app.server",""),
	
	BASIC_DB_CONF_XML			("app.config.xml.dbcon","This will hold all the DB connection related properties."),
	BASIC_CUSTOM_CONF_PROP		("app.config.prop.custom","This will hold all application related custom properties."),
	BASIC_QUERIES_CONF_XML		("app.config.xml.queries","This will hold all the DB quries for the application.")
	;
	
	String key;
	String purpose;
	
	private BasicConfigConstants(String key, String purpose) {
		this.key = key;
		this.purpose = purpose;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	
}
