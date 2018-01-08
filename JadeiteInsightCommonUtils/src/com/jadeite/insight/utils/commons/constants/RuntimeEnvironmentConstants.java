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
public enum RuntimeEnvironmentConstants {

	SYSTEM_FILE_SEPARATOR ("file.separator", 	"Character that separates components of a file path. This is \"/\" on UNIX and \"\\\" on Windows."),
	SYSTEM_JAVA_CLASSPATH ("java.class.path",	"Path used to find directories and JAR archives containing class files. Elements of the class path are separated by a platform-specific character specified in the path.separator property."),
	SYSTEM_JAVA_HOME      ("java.home", 		"Installation directory for Java Runtime Environment (JRE)"),
	SYSTEM_JAVA_VENDOR    ("java.vendor",		"JRE vendor name"),
	SYSTEM_JAVA_VURL	  ("java.vendor.url",	"JRE vendor URL"),
	SYSTEM_JAVA_VERSION	  ("java.version",		"JRE version number"),
	SYSTEM_LINE_SEPARATOR ("line.separator",	"Sequence used by operating system to separate lines in text files"),
	SYSTEM_OS			  ("os.arch", 			"Operating system architecture"),
	SYSTEM_OS_NAME		  ("os.name", 			"Operating system name"),
	SYSTEM_OS_VERSION	  ("os.version", 		"Operating system version"),
	SYSTEM_PATH_SEPARATOR ("path.separator", 	"Path separator character used in java.class.path"),
	SYSTEM_USER_DIR		  ("user.dir", 			"User working directory"),
	SYSTEM_USER_HOME	  ("user.home", 		"User home directory"),
	SYSTEM_USER_NAME	  ("user.name", 		"User account name");
	
	String key;
	String purpose;
	
	private RuntimeEnvironmentConstants(String key, String purpose) {
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
