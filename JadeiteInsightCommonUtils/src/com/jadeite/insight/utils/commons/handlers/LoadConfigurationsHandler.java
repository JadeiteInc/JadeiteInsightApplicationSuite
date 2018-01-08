/**
 * ECPS Health Center
 */
package com.jadeite.insight.utils.commons.handlers;

import static com.jadeite.insight.utils.commons.constants.ConfigurationsConstants.BASIC_CONFIG;
import static com.jadeite.insight.utils.commons.constants.ConfigurationsConstants.BASIC_CONFIG_DIR_NAME;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jadeite.insight.utils.commons.constants.ConfigurationsConstants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Sep 17, 2017
 * 
 *
 */
public class LoadConfigurationsHandler {
	
	private static Logger logger = null;
	private static String applicationName = null;
	static {
		logger = LogManager.getLogger(LoadConfigurationsHandler.class);
	}
	
	/**
	 * 
	 */
	public LoadConfigurationsHandler() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param configPath
	 * @param baseConfigName
	 * @return
	 * @throws ConfigurationException 
	 */
	public static Map<ConfigurationsConstants, Object> loadConfigurations(String configPath, String baseConfigName) throws ConfigurationException {
		Map<ConfigurationsConstants, Object> applicationConfigurations = new HashMap<ConfigurationsConstants, Object>(10);
		//Load the Basic Config Properties.
		try {
			applicationConfigurations.put(BASIC_CONFIG, loadBaseConfiguaion(configPath, baseConfigName));
			logger.debug(BASIC_CONFIG.getName() + StringUtils.SPACE + ((Configuration)applicationConfigurations.get(BASIC_CONFIG)));
			
			//Read the Basic Config for loading other APP CONFIG Files
			Configuration basicConfiguration = (Configuration) applicationConfigurations.get(BASIC_CONFIG);
			
		} catch (ConfigurationException e) {
			logger.error("Loading of Configurations Failed", e); 
			throw e;
		}
		return applicationConfigurations;
	}
	
	/**
	 * 
	 * @param configPath
	 * @param baseConfigName
	 * @return
	 * @throws ConfigurationException 
	 */
	private static Configuration loadBaseConfiguaion(String configPath, String baseConfigName) throws ConfigurationException { 
		
		Configurations configurations = new Configurations();
		Configuration configuration   = null;
		try {
			configuration = configurations.properties(new File(configPath.concat(File.separator).concat(BASIC_CONFIG_DIR_NAME.getName()).concat(File.separator).concat(baseConfigName))); 
		} catch (ConfigurationException e) {
			logger.error("Loading of - ".concat(baseConfigName).concat(" failed."), e);
			throw e;
		}
		return configuration;
	}
}
