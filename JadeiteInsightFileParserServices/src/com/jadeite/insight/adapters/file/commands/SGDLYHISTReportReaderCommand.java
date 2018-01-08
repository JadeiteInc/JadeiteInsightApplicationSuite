/**
 * ECPS Health Center
 */
package com.bns.ecps.hc.finmon.commands;

import static com.bns.ecps.hc.finmon.constants.CommonProcessorInputOutputConstants.CANONICAL_SGDLYHIST_DATA;
import static com.bns.ecps.hc.finmon.constants.CommonProcessorInputOutputConstants.PARSED_SGDLYHIST_DATA;
import static com.bns.ecps.hc.finmon.constants.CommonProcessorInputOutputConstants.SOURCE_FILE_DETAILS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import com.bns.ecps.hc.finmon.constants.CanonicalSGDLYHISTReportDataConstants;
import com.bns.ecps.hc.finmon.constants.CommandContextConstants;
import com.bns.ecps.hc.finmon.processors.SGDLYHISTDataTransformerProcessor;
import com.bns.ecps.hc.finmon.processors.SGDLYHISTFileParserProcessor;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 20, 2017
 * 
 *
 */
public class SGDLYHISTReportReaderCommand implements Command {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean execute(Context commandContext) throws Exception {
		
		//Get the 
		String sgDailyHistFilepath = String.valueOf(commandContext.get(CommandContextConstants.SG_DAILY_HISTORY_FILE_PATH));
		
		//--------- SGDLYHISTFileParserProcessor ----- START --
		Map<Object, Object> sgdlyhistFileParserProcessorInput  = new HashMap<Object, Object>(10);
		Map<Object, Object> sgdlyhistFileParserProcessorOutput = new HashMap<Object, Object>(10);
		SGDLYHISTFileParserProcessor sgdlyhistFileParserProcessor = new SGDLYHISTFileParserProcessor();
		
		//Set the value to Process Input
		sgdlyhistFileParserProcessorInput.put(SOURCE_FILE_DETAILS, sgDailyHistFilepath);
		sgdlyhistFileParserProcessorOutput = sgdlyhistFileParserProcessor.process(sgdlyhistFileParserProcessorInput);
		//--------- SGDLYHISTFileParserProcessor ----- END --
		
		
		//--------- SGDLYHISTDataTransformerProcessor ----- START --
		Map<Object, Object> dataTransformerProcessorInput  = new HashMap<Object, Object>(10);
		Map<Object, Object> dataTransformerProcessorOutput = new HashMap<Object, Object>(10);
		SGDLYHISTDataTransformerProcessor dataTransformerProcessor = new SGDLYHISTDataTransformerProcessor();
		
		//Set the value of the Parsed Data
		dataTransformerProcessorInput.put(PARSED_SGDLYHIST_DATA, (List<Map<Enum<?>, String>>) sgdlyhistFileParserProcessorOutput.get(PARSED_SGDLYHIST_DATA));
		dataTransformerProcessorOutput = dataTransformerProcessor.process(dataTransformerProcessorInput);
		//--------- SGDLYHISTDataTransformerProcessor ----- END --
		
		
		//Set everything to Command Context
		commandContext.put(CommandContextConstants.SG_DAILY_HISTORY_RAW_DATA, (List<Map<Enum<?>, String>>) sgdlyhistFileParserProcessorOutput.get(PARSED_SGDLYHIST_DATA));
				
		commandContext.put(CommandContextConstants.SG_DAILY_HISTORY_CANONICAL_DATA, (List<Map<CanonicalSGDLYHISTReportDataConstants, String>>) dataTransformerProcessorOutput.get(CANONICAL_SGDLYHIST_DATA));
			
		
		/*List<Map<CanonicalSGDLYHISTReportDataConstants, String>> normalizedReportInfoList = 
				(List<Map<CanonicalSGDLYHISTReportDataConstants, String>>) dataTransformerProcessorOutput.get(CommonProcessorInputOutputConstants.CANONICAL_SGDLYHIST_DATA);
		*/
		
		
		return false;
	}

}
