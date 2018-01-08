/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.commands;

import java.util.List;
import java.util.Map;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import com.jadeite.insight.adapters.file.constants.CanonicalSGDLYHISTReportDataConstants;
import com.jadeite.insight.adapters.file.constants.CommandContextConstants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 22, 2017
 * 
 *
 */
public class FinMonitorExcelGeneratorCommand implements Command {

	/* (non-Javadoc)
	 * @see org.apache.commons.chain.Command#execute(org.apache.commons.chain.Context)
	 */
	@Override
	public boolean execute(Context commandContext) throws Exception {
		
		List<Map<CanonicalSGDLYHISTReportDataConstants, String>> canonicalSGDLYHISTReportData = 
				(List<Map<CanonicalSGDLYHISTReportDataConstants, String>>) commandContext.get(CommandContextConstants.SG_DAILY_HISTORY_CANONICAL_DATA);
		
		return false;
	}
	
}
