/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.processors;

import static com.jadeite.insight.adapters.file.constants.CommonConstants.CLOSING_BALANCE_TXT;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.DAILY_BALANCE_TXT;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.LINE_NUM_1;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.LINE_NUM_2;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.LINE_NUM_3;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.OPENING_BALANCE_TXT;
import static com.jadeite.insight.adapters.file.constants.CommonProcessorInputOutputConstants.PARSED_SGDLYHIST_DATA;
import static com.jadeite.insight.adapters.file.constants.CommonProcessorInputOutputConstants.SOURCE_FILE_DETAILS;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bns.ecps.hc.commons.interfaces.Processor;
import com.jadeite.insight.adapters.file.constants.SGDLYHISTHeaderFormatConstants.FIRST_LINE;
import com.jadeite.insight.adapters.file.constants.SGDLYHISTHeaderFormatConstants.SECOND_LINE;
import com.jadeite.insight.adapters.file.constants.SGDLYHISTHeaderFormatConstants.THIRD_LINE;
import com.jadeite.insight.adapters.file.constants.SGDLYHISTReportDataFormatConstants.ACCOUNT_NUMBER_LINE;
import com.jadeite.insight.adapters.file.constants.SGDLYHISTReportDataFormatConstants.CLOSING_BALANCE_LINE;
import com.jadeite.insight.adapters.file.constants.SGDLYHISTReportDataFormatConstants.DAILY_BALANCE_LINE;
import com.jadeite.insight.adapters.file.constants.SGDLYHISTReportDataFormatConstants.OPENING_BALANCE_LINE;
import com.jadeite.insight.adapters.file.constants.SGDLYHISTReportDataFormatConstants.REPORT_DATA_LINE;
import com.jadeite.insight.adapters.file.constants.SGDLYHISTReportDataHeaderFormatConstants;

/**
 * @author Jay Mukherjee
 * 
 * @date Nov 9, 2017
 * 
 *
 */
public class SGDLYHISTFileParserProcessor implements Processor {

	private static Logger logger = null;
	
	static {
		logger = LogManager.getLogger(SGDLYHISTFileParserProcessor.class);
	}
	
	@Override
	public Map<Object, Object> process(Map<Object, Object> processInput) throws Exception {
		Map<Object, Object> processOutput = new HashMap<Object, Object>(10);
		try {			
			logger.info("Getting ready to parse - "+processInput.get(SOURCE_FILE_DETAILS));
			List<String> linesOnFile = convertSGDLYHISTtoList((String) processInput.get(SOURCE_FILE_DETAILS));
			logger.info("Completed Parsing the file using Stream and converted that to list of lines. List size - "+linesOnFile.size());
			
			/**
			 * Data Structure Holding the Parsed Report Information.
			 */
			List<Map<Enum<?>, String>> parsedReportInformation = new ArrayList<Map<Enum<?>, String>>(100);
			
			List<String> reportPageHeader 	= null;
			List<String> reportHeaderData   = null;
			List<String> reportGLData   	= null;
			
			boolean headerSection = false;
			boolean reportDataHeaderSection = false;
			boolean reportDataSection = false;
			
			int reportHeaderDataCounter = 0;
			
			logger.info("Starting to Parse each line on the file."); 
			for (int i = 0; i < linesOnFile.size(); i++) {
				String line  = linesOnFile.get(i);
				
				//Check if this is the start of New Page or Header Section
				if(isHeaderStart(line)) {
					//If TRUE - Then set the headerSection is true and initiate the Array for Header Section.
					headerSection = true;
					reportDataSection = false;
					reportPageHeader = new ArrayList<String>(3);
					
				} 
				
				//If FALSE - Then check if this is End of Header section
				//If its end of header section and "headerSection" was TRUE then reset that to False and send the header for Parsing. 
				//But, since its End of Page Header, the next section will be "reportDataHeaer" to TRUE		
				if(isEndOfHeader(line) && headerSection) {
					headerSection = false;
					
					reportDataHeaderSection = true;
					reportDataSection = false;
					reportHeaderData = new ArrayList<String>(2);
					reportHeaderDataCounter = 1;
					
					parseSGDLYHISTHeader(reportPageHeader, parsedReportInformation);
					
				} 

				if(reportDataHeaderSection && reportHeaderDataCounter >= 3) {
					reportDataHeaderSection = false;
					reportHeaderDataCounter = 0;
					
					reportDataSection = true;
					reportGLData = new ArrayList<String>(50);
					
					parseSGDLYHISTReportDataHeader(reportHeaderData, parsedReportInformation);
				}
						
				//If its the "headerSection" then put the line into reportHeader list for parsing.
				if(headerSection) {
					reportPageHeader.add(line);
				} 
				
				//If its the "reportHeaderSection" then put the line into reportHeaderData list for parsing.
				if(reportDataHeaderSection) {
					reportHeaderData.add(line);
					reportHeaderDataCounter++;
				}
				
				//If its the "reportHeaderSection" then put the line into reportHeaderData list for parsing.
				if(reportDataSection) {
					reportGLData.add(line);
				}
				
				if(reportGLData != null && reportGLData.size()>0) {
					parseSGDLYHISTReportDataGL(reportGLData, parsedReportInformation);
					reportGLData = new ArrayList<String>(50);
				}
			}
					
			processOutput.put(PARSED_SGDLYHIST_DATA, parsedReportInformation);			
			logger.info("Completed Parsing all the data into HashMap. List of HashMap size - "+parsedReportInformation.size()); 
//			int i = 1;
//			for (Map<Enum<?>, String> map : parsedReportInformation) {
//				logger.debug(i +" --> "+ map); 
//				i++;
//			}
			
		} catch (IOException e) {
			logger.error("Processing the Source File "+(String) processInput.get(SOURCE_FILE_DETAILS)+" failed.", e);
			throw(e);
		}
		
		
		
		return processOutput;
	}

	
	/**
	 * Parse the source flat file and convert that to a list of String objects for each line.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private List<String> convertSGDLYHISTtoList(String fileName) throws IOException {

		List<String> linesOnFile = new ArrayList<>();

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.ISO_8859_1)) {

			linesOnFile = stream.map(String::toUpperCase).collect(Collectors.toList());

		} catch (IOException e) {
			logger.error("Parsing the File and converting to List failed.", e);
			throw(e);
		}

		//linesOnFile.forEach(System.out::println);

		return linesOnFile;
	}
	
	/**
	 * Parse the Report/Page Header
	 * 
	 * @param headerLines
	 * @throws Exception
	 */
	private void parseSGDLYHISTHeader(List<String> headerLines, List<Map<Enum<?>, String>> parsedReportInformation) throws Exception {
		
		Map<Enum<?>, String> headerValuesMap = new HashMap<Enum<?>, String>(10);
		
		if (headerLines != null && headerLines.size()==3) {
			
			for (int i = 1; i <= headerLines.size(); i++) {
				if(i==Integer.parseInt(LINE_NUM_1.getValue())) {
					for(FIRST_LINE firstLine : FIRST_LINE.values()) {
						headerValuesMap.put(firstLine, headerLines.get(i-1).substring(firstLine.getStartPosition(), firstLine.getStartPosition() + firstLine.getLength()).trim());
					}
				}
				
				if(i==Integer.parseInt(LINE_NUM_2.getValue())) {
					for(SECOND_LINE secondLine : SECOND_LINE.values()) { 
						headerValuesMap.put(secondLine, headerLines.get(i-1).substring(secondLine.getStartPosition(), secondLine.getStartPosition() + secondLine.getLength()).trim());
					}
				}
				
				if(i==Integer.parseInt(LINE_NUM_3.getValue())) {
					for(THIRD_LINE thirdLine : THIRD_LINE.values()) { 
						headerValuesMap.put(thirdLine, headerLines.get(i-1).substring(thirdLine.getStartPosition(), thirdLine.getStartPosition() + thirdLine.getLength()).trim());
					}
				}
			}
			parsedReportInformation.add(headerValuesMap);
			
//			logger.debug("----------------------------------------------------------------------------------------------------------------------------------------------------------");
//			logger.debug(parsedReportInformation);
			
			
		} else {
			Exception e = new Exception("Header List is null or not well formed.");
			logger.error(e);
			throw e;
		}
	}
	
	
	/**
	 * 
	 * @param headerLines
	 * @throws Exception
	 */
	private void parseSGDLYHISTReportDataHeader(List<String> reportDataHeaderLines, List<Map<Enum<?>, String>> parsedReportInformation) throws Exception {
		
		Map<Enum<?>, String> reportDataHeaderValuesMap = new HashMap<>(10);
		
		if (reportDataHeaderLines != null && reportDataHeaderLines.size()==2) {
			
			for (int i = 1; i <= reportDataHeaderLines.size(); i++) {
				for(SGDLYHISTReportDataHeaderFormatConstants reportDataHeader : SGDLYHISTReportDataHeaderFormatConstants.values()) {
						
					if(i==Integer.parseInt(LINE_NUM_1.getValue())) {
						reportDataHeaderValuesMap.put(reportDataHeader, 
								reportDataHeaderLines.get(i-1).substring(reportDataHeader.getStartPosition(), reportDataHeader.getStartPosition() + reportDataHeader.getLength()).trim());
					}
					
					if(i==Integer.parseInt(LINE_NUM_2.getValue())) {
						reportDataHeaderValuesMap.put(reportDataHeader, 
								reportDataHeaderValuesMap.get(reportDataHeader).concat("-").concat(
								reportDataHeaderLines.get(i-1).substring(reportDataHeader.getStartPosition(), reportDataHeader.getStartPosition() + reportDataHeader.getLength()).trim()));
					}
										
				}
								
			}
			parsedReportInformation.add(reportDataHeaderValuesMap);
//			logger.debug("**********************************************************************************************************************************************");
//			logger.debug(reportDataHeaderValuesMap);
			
		} else {
			Exception e = new Exception("Report Data Header List is null or not well formed.");
			logger.error(e);
			throw e;
		}
	}
	
	/**
	 * 
	 * @param reportGLDataLines
	 * @throws Exception 
	 */
	private void parseSGDLYHISTReportDataGL(List<String> reportGLDataLines, List<Map<Enum<?>, String>> parsedReportInformation) throws Exception {
		
		Map<Enum<?>, String> reportDataGLAccountMap  = new HashMap<>(10);
		Map<Enum<?>, String> reportDataGLOpenBalMap  = new HashMap<>(10);
		Map<Enum<?>, String> reportDataGLDailyBalMap = new HashMap<>(10);
		Map<Enum<?>, String> reportDataGLCloseBalMap = new HashMap<>(10);
		Map<Enum<?>, String> reportDataGLValuesMap = new HashMap<>(10);
		
		if (reportGLDataLines != null && reportGLDataLines.size()>0) {
			
			for (int i = 1; i <= reportGLDataLines.size(); i++) {
				String line = reportGLDataLines.get(i-1);
				
				if(line.length()<133) {
					logger.error("The line is not 133 characters" + line);
					throw new Exception("The line is not 133 characters" + line);
				} else if (line.length() > 133) {
					logger.error("The line is more than 133 characters" + line);
					throw new Exception("The line is more than 133 characters" + line);
				}
				
				if(isAccountLine(line)) {
					for(ACCOUNT_NUMBER_LINE accountNumLine : ACCOUNT_NUMBER_LINE.values()) {
						reportDataGLAccountMap.put(accountNumLine, line.substring(accountNumLine.getStartPosition(),accountNumLine.getStartPosition() + accountNumLine.getLength()).trim());
					}	
					parsedReportInformation.add(reportDataGLAccountMap);
//					logger.debug(reportDataGLAccountMap); 
				} else if(isValidLine(line, OPENING_BALANCE_TXT.getValue())) {
					for(OPENING_BALANCE_LINE openingBalLine : OPENING_BALANCE_LINE.values()) {
						reportDataGLOpenBalMap.put(openingBalLine, line.substring(openingBalLine.getStartPosition(),openingBalLine.getStartPosition() + openingBalLine.getLength()).trim());
					}
					parsedReportInformation.add(reportDataGLOpenBalMap);
//					logger.debug(reportDataGLOpenBalMap); 
				} else if(isValidLine(line, DAILY_BALANCE_TXT.getValue())) {
					for(DAILY_BALANCE_LINE dailyBalLine : DAILY_BALANCE_LINE.values()) {
						reportDataGLDailyBalMap.put(dailyBalLine, line.substring(dailyBalLine.getStartPosition(),dailyBalLine.getStartPosition() + dailyBalLine.getLength()).trim());
					}
					parsedReportInformation.add(reportDataGLDailyBalMap);
//					logger.debug(reportDataGLDailyBalMap); 
				} else if(isValidLine(line, CLOSING_BALANCE_TXT.getValue())) {
					for(CLOSING_BALANCE_LINE closingBalLine : CLOSING_BALANCE_LINE.values()) {
						reportDataGLCloseBalMap.put(closingBalLine, line.substring(closingBalLine.getStartPosition(),closingBalLine.getStartPosition() + closingBalLine.getLength()).trim());
					}
					parsedReportInformation.add(reportDataGLCloseBalMap);
//					logger.debug(reportDataGLCloseBalMap); 
				} else {
					for(REPORT_DATA_LINE reportDataGLLine : REPORT_DATA_LINE.values()) {
						reportDataGLValuesMap.put(reportDataGLLine, line.substring(reportDataGLLine.getStartPosition(),reportDataGLLine.getStartPosition() + reportDataGLLine.getLength()).trim());
					}
					parsedReportInformation.add(reportDataGLValuesMap);
//					logger.debug(reportDataGLValuesMap); 
				}
				
			}
			
		} else {
			Exception e = new Exception("Report GL Data List is null or not well formed.");
			logger.error(e);
			throw e;
		}
	}
	
	/**
	 * 
	 * @param line
	 * @return
	 */
	private boolean isHeaderStart(String line) {
		return line != null && line.substring(0, 1).equalsIgnoreCase("1")?true:false;
	}
	
	/**
	 * 
	 * @param line
	 * @return
	 */
	private boolean isEndOfHeader(String line) {
		return line != null && line.substring(0, 1).equalsIgnoreCase("0")?true:false;
	}
	
	/**
	 * 
	 * @param line
	 * @return
	 */
	private boolean isAccountLine(String line) {
		return line != null && line.substring(1, 2).trim().isEmpty()?false:true;
	}
	
	/**
	 * 
	 * @param line
	 * @param text
	 * @return
	 */
	private boolean isValidLine(String line, String text) {
		return line != null && line.contains(text)?true:false;
	}
}
