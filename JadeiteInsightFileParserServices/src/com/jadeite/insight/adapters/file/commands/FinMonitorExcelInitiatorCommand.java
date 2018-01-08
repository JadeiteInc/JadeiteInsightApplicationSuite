/**
 * ECPS Health Center
 */
package com.bns.ecps.hc.finmon.commands;

import static com.bns.ecps.hc.finmon.constants.CommandContextConstants.FIN_MONITOR_CONFIG_DIR_PATH;
import static com.bns.ecps.hc.finmon.constants.CommandContextConstants.FIN_MONITOR_SUMMARY_EXCEL_PATH;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 24, 2017
 * 
 *
 */
public class FinMonitorExcelInitiatorCommand implements Command {

	private static final String finMonitorExcelTemplateName 		= "ECPS-Financial-Monitor-Summary-template.xlsx";
	private static final String finMonitorExcelTemplateSourcePath 	= "Templates";
	private static final String finMonitorExcelTargetPath			= "MonitorOutput";
	
	private static Logger logger = null;
	
	static {
		logger = LogManager.getLogger(FinMonitorExcelInitiatorCommand.class);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.commons.chain.Command#execute(org.apache.commons.chain.Context)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean execute(Context commandContext) throws Exception {
		
		Date currentDate					= new Date();
		
		String finMonitorSpace 				= commandContext.get(FIN_MONITOR_CONFIG_DIR_PATH).toString();
		String finMonitorTemplateSourcePath	= finMonitorSpace.concat(File.separator).concat(finMonitorExcelTemplateSourcePath).concat(File.separator).concat(finMonitorExcelTemplateName);
		String finMonitorTemplateTargetPath	= finMonitorSpace.concat(File.separator).concat(finMonitorExcelTargetPath).concat(File.separator)
															 .concat(finMonitorExcelTemplateName.replaceAll("template", currentDate.getTime()+""));
		
		logger.info("Source Excel Template : "+finMonitorTemplateSourcePath);
		logger.info("Target Excel Template : "+finMonitorTemplateTargetPath);
		
		File sourceFile = new File(finMonitorTemplateSourcePath);
		File targetFile = new File(finMonitorTemplateTargetPath);
		
		Files.copy(sourceFile.toPath(), targetFile.toPath());
		
		commandContext.put(FIN_MONITOR_SUMMARY_EXCEL_PATH, targetFile.getAbsolutePath());
		logger.info(targetFile.getAbsolutePath() + " created successfully."); 
		
		return false;
	}

}
