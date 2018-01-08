/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.commands;

import static com.jadeite.insight.adapters.file.constants.CommandContextConstants.SG_DAILY_HISTORY_CANONICAL_CIDC_DATA;
import static com.jadeite.insight.adapters.file.constants.CommandContextConstants.SG_DAILY_HISTORY_CANONICAL_DATA;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.CA_23069;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.HA_95703;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.MO_83451;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.TO_65052;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.VA_93120;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.WI_05827;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jadeite.insight.adapters.file.constants.CanonicalSGDLYHISTReportDataConstants;
import com.jadeite.insight.adapters.file.constants.CommonConstants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 28, 2017
 * 
 *
 */
public class SGDLYHISTExtractCIDCDataCommand implements Command {

	private static Logger logger 							= null;
	private static final  String[] cidcAndSymcorTransits	= {HA_95703.getValue(),MO_83451.getValue(),TO_65052.getValue(),WI_05827.getValue(),CA_23069.getValue(),VA_93120.getValue()};
		
	static {
		logger = LogManager.getLogger(SGDLYHISTExtractCIDCDataCommand.class);
	}
	/* (non-Javadoc)
	 * @see org.apache.commons.chain.Command#execute(org.apache.commons.chain.Context)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean execute(Context commandContext) throws Exception {

		@SuppressWarnings("unchecked")
		List<Map<CanonicalSGDLYHISTReportDataConstants, String>> canonicalSGDLYHISTReportData = (List<Map<CanonicalSGDLYHISTReportDataConstants, String>>) commandContext.get(SG_DAILY_HISTORY_CANONICAL_DATA);
		
		List<Map<CanonicalSGDLYHISTReportDataConstants, String>> filteredCanonicalCIDCData = new ArrayList<Map<CanonicalSGDLYHISTReportDataConstants, String>>(5000);
		 
		
		for (int index = 0; index < canonicalSGDLYHISTReportData.size(); index++) {
			Map<CanonicalSGDLYHISTReportDataConstants, String> oneRow = (Map<CanonicalSGDLYHISTReportDataConstants, String>) canonicalSGDLYHISTReportData.get(index); 
			String routingTransit = oneRow.get(CanonicalSGDLYHISTReportDataConstants.ROUTING_TRANSIT);
			
			if(Arrays.asList(cidcAndSymcorTransits).contains(routingTransit)){
				filteredCanonicalCIDCData.add(oneRow);
			} 	
		}
		
		
		logger.info("Before Filterting for 6 CIDCs the number of Rows are - "+canonicalSGDLYHISTReportData.size()); 
		//CleanUp the Canonical Data from Memory
		canonicalSGDLYHISTReportData.clear();
		logger.info("After Filtered only for 6 CIDCs the number of Rows are - "+canonicalSGDLYHISTReportData.size());
		commandContext.put(SG_DAILY_HISTORY_CANONICAL_CIDC_DATA, filteredCanonicalCIDCData);
		
		logger.info("The new data after the filtering is - "+filteredCanonicalCIDCData.size()); 
		
		return false;
	}

}
