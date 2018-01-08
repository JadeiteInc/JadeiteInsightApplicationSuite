/**
 * ECPS Health Center
 */
package com.jadeite.insight.utils.commons.interfaces;

import java.util.Map;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Sep 15, 2017
 * 
 *
 */
public interface Processor {

	public Map<Object, Object> process(Map<Object, Object> processInput) throws Exception;
}
