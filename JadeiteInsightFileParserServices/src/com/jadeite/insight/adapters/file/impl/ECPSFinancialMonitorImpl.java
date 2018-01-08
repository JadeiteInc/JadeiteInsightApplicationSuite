/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.impl;

import static com.jadeite.insight.adapters.file.constants.CommandContextConstants.FIN_MONITOR_CONFIG_DIR_PATH;
import static com.jadeite.insight.adapters.file.constants.CommandContextConstants.SG_DAILY_HISTORY_FILE_PATH;

import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import com.jadeite.insight.adapters.file.beans.FinMonCommandContext;
import com.jadeite.insight.adapters.file.commands.FinMonitorCommandCatalog;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 20, 2017
 * 
 *
 */
public class ECPSFinancialMonitorImpl {

	public static void main(String... args) {
		
		new ECPSFinancialMonitorImpl().extractPostedAccountingFinancialEntries(args);
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void extractPostedAccountingFinancialEntries(String... agrs) {
		
		/*
		 * Create the Command Chain context
		 */
		// -- Start of Command Context Setup --
		Context finMonCommandContext = new FinMonCommandContext();
		//Fetch the SG File Path and set that to Command Context.
		String sgDailyHistoryFileName = "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorSource\\SGSourceFiles\\SG_SG038RDR_RPT_HQ3HR3CP_20171222_090135.dat"; 
		
		String cihIncomingGLHAFileName = "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorSource\\CIHFileSource\\CIH_GLEPPOSTING_ALL_HA_010084002_20171130061921_EXT_RMAHVSHA.dat";
		String cihIncomingGLMOFileName = "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorSource\\CIHFileSource\\CIH_GLEPPOSTING_ALL_MO_010084002_20171130071716_EXT_RMAHVSMO.dat";
		String cihIncomingGLTOFileName = "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorSource\\CIHFileSource\\CIH_GLEPPOSTING_ALL_TO_010084002_20171130072001_EXT_RMAHVSTO.dat";
		String cihIncomingGLWIFileName = "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorSource\\CIHFileSource\\CIH_GLEPPOSTING_ALL_WI_010084002_20171130091408_EXT_RMAHVSWI.dat";
		String cihIncomingGLCAFileName = "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorSource\\CIHFileSource\\CIH_GLEPPOSTING_ALL_CA_010084002_20171130093633_EXT_RMAHVSCA.dat";
		String cihIncomingGLVAFileName = "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorSource\\CIHFileSource\\CIH_GLEPPOSTING_ALL_VA_010084002_20171130094238_EXT_RMAHVSVA.dat";
		
		String cihIIEInboundGLFileName = "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorSource\\CIHFileSource\\CIH_IIEGLEPPOSTING_ALL_TO_010084002_20171130100001_EXT_RMAHACSI.dat";
		
		String cihOutboundGLFileName   = "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorSource\\CIHFileSource\\CIH_GLEPPOSTING_ALL_TO_010084002_20171130094227_EXT_RMAHCRDS.dat";
		
		String cihInternalAdjFileName  = "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorSource\\CIHFileSource\\CIH_GLEPPOSTING_ALL_TO_X111X002_20171130094231_EXT_RMAHADJI.dat";
		
		//Set the SG File Path and set that to Command Context.
		finMonCommandContext.put(SG_DAILY_HISTORY_FILE_PATH, sgDailyHistoryFileName);
		finMonCommandContext.put(FIN_MONITOR_CONFIG_DIR_PATH, "C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace");
		// -- End of Command Context Setup --
		
		Catalog catalog = new FinMonitorCommandCatalog();
		Command finMonitorCommandChain = catalog.getCommand("FINANCIAL_MONITOR");
		
		try {
			finMonitorCommandChain.execute(finMonCommandContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
	}
}
