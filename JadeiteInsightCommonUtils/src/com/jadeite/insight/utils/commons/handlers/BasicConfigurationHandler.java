/**
 * ECPS Health Center
 */
package com.jadeite.insight.utils.commons.handlers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;

import com.jadeite.insight.utils.commons.constants.BasicConfigConstants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Sep 17, 2017
 * 
 *
 */
public class BasicConfigurationHandler {

	private static Map <BasicConfigConstants, String> basicConfigResourceMap = new HashMap<BasicConfigConstants, String>(BasicConfigConstants.values().length);
	
	/**
	 * 
	 */
	public BasicConfigurationHandler() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param basicConfiguration
	 * @return
	 */
	public static Map<BasicConfigConstants, String> loadBasicConfigResources(Configuration basicConfiguration){
		for(BasicConfigConstants basicConfigConstant : BasicConfigConstants.values()) {
			basicConfigResourceMap.put(basicConfigConstant, basicConfiguration.getString(basicConfigConstant.getKey()));
		}
		return basicConfigResourceMap;
	}
	
	/**
	 * 
	 * @param basicConfigConstant
	 * @return
	 */
	public static String getProeprty(BasicConfigConstants basicConfigConstant) {
		return basicConfigResourceMap.get(basicConfigConstant);
	}
}
