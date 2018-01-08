/**
 * ECPS Health Center
 */
package com.bns.ecps.hc.finmon.commands;

import org.apache.commons.chain.impl.ChainBase;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 22, 2017
 * 
 *
 */
public class FinMonitorCommandChain extends ChainBase {

	private static Logger logger = null;
	static {
		logger = LogManager.getLogger(FinMonitorCommandChain.class);
	}
	
	/**
	 * 
	 */
	public FinMonitorCommandChain() {
		super();
		
		logger.debug("Instanciating FinMonitorCommandCatalog constructor."); 
		
		/*
		 * Purpose of this command is to get the SGDLYHST Report 
		 * and then Parse the Report to get all the data and 
		 * convert that to a Canonical format.  
		 */
		addCommand(new SGDLYHISTReportReaderCommand());
		
		/*
		 * Create a JSON file for the Mongo DB Load 
		 */
		//addCommand(new SGDLYHISTConvertedJSONCommand()); 
		
		addCommand(new FinMonitorExcelInitiatorCommand()); 
		
		addCommand(new SGDLYHISTExtractCIDCDataCommand());
		
		//addCommand(new SGDLYHISTConvertCSVCommand());
		
		addCommand(new SGDLYHISTSummaryExcelUpdaterCommand()); 
	}
}
