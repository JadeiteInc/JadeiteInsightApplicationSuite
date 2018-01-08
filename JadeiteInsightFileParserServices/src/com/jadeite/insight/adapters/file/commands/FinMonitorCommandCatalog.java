/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.commands;

import org.apache.commons.chain.impl.CatalogBase;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 22, 2017
 * 
 *
 */
public class FinMonitorCommandCatalog extends CatalogBase{

	private static Logger logger = null;
	static {
		logger = LogManager.getLogger(FinMonitorCommandCatalog.class);
	}
	
	public FinMonitorCommandCatalog() {
		super();
		
		logger.debug("Instanciating FinMonitorCommandCatalog constructor."); 
		
		/*
		 * 
		 */
		addCommand("FINANCIAL_MONITOR", new FinMonitorCommandChain()); 
		
		
	}
	
}
