/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.beans;

import java.util.List;
import java.util.Map;

import org.apache.commons.chain.impl.ContextBase;

import com.jadeite.insight.adapters.file.constants.CanonicalSGDLYHISTReportDataConstants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 20, 2017
 * 
 *
 */
public class FinMonCommandContext extends ContextBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6717710035460132429L;

	/*
	 * The Source SG Daily History File information/Path 
	 */
	String sgDailyHistoryFilePath = null;
		
	/*
	 * This will hold the SG DAILY HISTORY File Parsed Information.
	 */
	List<Map<Enum<?>, String>> sgDailyHistoryRawData = null;
	
	/*
	 * This will hold the Canonical PARSED DATA for SG DAILY HISTORY
	 */
	Map<CanonicalSGDLYHISTReportDataConstants, String> sgDailyHistoryCanonicalData = null;
	
	/*
	 * ECPS Related SG GL Posting Information
	 */
	
}
