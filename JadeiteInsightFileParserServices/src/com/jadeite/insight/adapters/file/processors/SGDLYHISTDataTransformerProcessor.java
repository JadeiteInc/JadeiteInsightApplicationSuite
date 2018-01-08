/**
 * ECPS Health Center
 */
package com.bns.ecps.hc.finmon.processors;

import static com.bns.ecps.hc.finmon.constants.CanonicalSGDLYHISTReportDataConstants.CLOSING_BALANCE;
import static com.bns.ecps.hc.finmon.constants.CanonicalSGDLYHISTReportDataConstants.DAILY_BALANCE;
import static com.bns.ecps.hc.finmon.constants.CommonProcessorInputOutputConstants.PARSED_SGDLYHIST_DATA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bns.ecps.hc.commons.interfaces.Processor;
import com.bns.ecps.hc.finmon.constants.CanonicalSGDLYHISTReportDataConstants;
import com.bns.ecps.hc.finmon.constants.CommonProcessorInputOutputConstants;
import com.bns.ecps.hc.finmon.constants.SGDLYHISTHeaderFormatConstants.FIRST_LINE;
import com.bns.ecps.hc.finmon.constants.SGDLYHISTReportDataFormatConstants;
import com.bns.ecps.hc.finmon.constants.SGDLYHISTReportDataFormatConstants.ACCOUNT_NUMBER_LINE;
import com.bns.ecps.hc.finmon.constants.SGDLYHISTReportDataFormatConstants.CLOSING_BALANCE_LINE;
import com.bns.ecps.hc.finmon.constants.SGDLYHISTReportDataFormatConstants.DAILY_BALANCE_LINE;
import com.bns.ecps.hc.finmon.constants.SGDLYHISTReportDataFormatConstants.OPENING_BALANCE_LINE;
import com.bns.ecps.hc.finmon.constants.SGDLYHISTReportDataFormatConstants.REPORT_DATA_LINE;
import com.bns.ecps.hc.finmon.constants.SGDLYHISTReportDataHeaderFormatConstants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 16, 2017
 * 
 *
 */
public class SGDLYHISTDataTransformerProcessor implements Processor {

	private static Logger logger = null;
	private static final SimpleDateFormat originalDateFormat 	= new SimpleDateFormat("yyMMdd");
	private static final SimpleDateFormat finalDateFormat 		= new SimpleDateFormat("yyyy/MM/dd");
	private static final String currentYearYY					= (Calendar.getInstance().get(Calendar.YEAR)+"").substring(2); 
	
	static {
		logger = LogManager.getLogger(SGDLYHISTDataTransformerProcessor.class);
	}
	
	/* (non-Javadoc)
	 * @see com.bns.ecps.hc.commons.interfaces.Processor#process(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> process(Map<Object, Object> processInput) throws Exception {
		
		Map<Object, Object> processOutput = new HashMap<Object, Object>(10);
		
		List<Map<Enum<?>, String>> parsedReportInformation 	= null;
		List<Map<CanonicalSGDLYHISTReportDataConstants, String>> normalizedReportInfoList = null;
		
		if(null != processInput.get(PARSED_SGDLYHIST_DATA)) {
			parsedReportInformation = (List<Map<Enum<?>, String>>) (processInput.get(PARSED_SGDLYHIST_DATA));
				
			/*
			 * Start Parsing the Report Data into Normalized Format
			 */
			Map<CanonicalSGDLYHISTReportDataConstants, String> canonicalDataMap = null;
			
			boolean reInstantiate = false;
			
			int listIndex = 0;
			
			int dailyBalanceIndex = 0;
			boolean dailyBalanceBoolean = false;
			
			int closingBalanceIndex = 0;
			boolean closingBalanceBoolean = false;
			
			String dailyBalance = null;
			String closingBalance = null;
			
			String previousTransactionDate = "";
			String entryValue = null;
			
			logger.info("Getting ready to Transform the parsed data into a Normalized data format.");
			for (Iterator<Map<Enum<?>, String>> iterator = parsedReportInformation.iterator(); iterator.hasNext();) {
				//Get the dataMap from the parsed data list.
				Map<Enum<?>, String> dataMap = (Map<Enum<?>, String>) iterator.next();
				
				if (canonicalDataMap == null) {
					canonicalDataMap = new TreeMap<CanonicalSGDLYHISTReportDataConstants, String>();
				}
				
				if (isPageHeader(dataMap)) {
//					logger.debug("Page Header - "+dataMap);
				}
				else if (isReportHeader(dataMap)) {
//					logger.debug("Report Header - "+dataMap);
					continue;
				}
				else if (isAccountNumberData(dataMap)) {
//					logger.debug("Account Number - "+dataMap); 
					dailyBalanceIndex 	= 0;
					closingBalanceIndex = 0;
				}
				else if (isOpeningBalance(dataMap)) {
//					logger.debug("Opening Balance - "+dataMap); 
				}
				else if (isReportData(dataMap)) {
//					logger.debug("Report Data - "+dataMap);
					reInstantiate = true;
					dailyBalanceIndex++;
					closingBalanceIndex++;
					String transDate = dataMap.get(SGDLYHISTReportDataFormatConstants.REPORT_DATA_LINE.TRANS_DATE);
					//Convert MMDD from SG file to YYYY/MM/DD
					if(!transDate.trim().isEmpty()) {
						previousTransactionDate = transDate;
					}
					//previousTransactionDate = currentYearYY.substring(2).concat(previousTransactionDate);
					//previousTransactionDate = finalDateFormat.format(originalDateFormat.parse(currentYearYY.concat(previousTransactionDate)));
					dataMap.put(SGDLYHISTReportDataFormatConstants.REPORT_DATA_LINE.TRANS_DATE, finalDateFormat.format(originalDateFormat.parse(currentYearYY.concat(previousTransactionDate))));
				}
				else if (isDailyBalance(dataMap)) {
//					logger.debug("Daily Balance - "+dataMap);
					dailyBalance = dataMap.get(DAILY_BALANCE_LINE.DAILY_BALANCE);
					dailyBalanceBoolean = true;
				}
				else if (isClosingBalance(dataMap)) {
//					logger.debug("Closing Balance - "+dataMap); 
					closingBalance = dataMap.get(CLOSING_BALANCE_LINE.CLOSING_BALANCE);
					closingBalanceBoolean = true;
				}
				else {
					logger.error("The Map is not identified. "+dataMap); 
				}
				
				
				for(CanonicalSGDLYHISTReportDataConstants normalizedReportConstants : CanonicalSGDLYHISTReportDataConstants.values()) {
					for (Map.Entry<Enum<?>, String> entry : dataMap.entrySet())
					{	
					    if(normalizedReportConstants.getKey().equalsIgnoreCase(entry.getKey().toString())) {
					    	entryValue = entry.getValue();
					    	if (normalizedReportConstants.equals(CanonicalSGDLYHISTReportDataConstants.OPENING_BALANCE) || normalizedReportConstants.equals(CanonicalSGDLYHISTReportDataConstants.AMOUNT)) {
					    		entryValue = entryValue.replaceAll(",", "");
							}
					    	canonicalDataMap.put(normalizedReportConstants, entryValue);
					    }
					}
				}
								
				if (reInstantiate) {
					if (null == normalizedReportInfoList) {
						normalizedReportInfoList = new ArrayList<>(parsedReportInformation.size());
					}
					normalizedReportInfoList.add(listIndex, canonicalDataMap);
					canonicalDataMap = new HashMap<>(canonicalDataMap);
					listIndex++;
//					logger.debug("Row-"+listIndex+" : "+ normalizedDataMap); 
					reInstantiate = false;
				}
				
				if(dailyBalanceBoolean) {
					updateDailyBalance(normalizedReportInfoList, dailyBalance, dailyBalanceIndex, listIndex);
					dailyBalanceBoolean = false;
					dailyBalanceIndex = 0;
				}
				
				if(closingBalanceBoolean) {
					updateClosingBalance(normalizedReportInfoList, closingBalance, closingBalanceIndex, listIndex);
					closingBalanceBoolean = false;
					closingBalanceIndex = 0;
				}
				
			}
			
		} else {
			logger.error("No data received for \"parsedReportInformation\" object."); 
			throw new Exception("No data received for \"parsedReportInformation\" object.");
		}
		
		/*for (Iterator iterator = normalizedReportInfoList.iterator(); iterator.hasNext();) {
			Map<NormalizedSGDLYHISTReportDataConstants, String> map = (Map<NormalizedSGDLYHISTReportDataConstants, String>) iterator.next();
			logger.debug(map); 
		}*/
		
		processOutput.put(CommonProcessorInputOutputConstants.CANONICAL_SGDLYHIST_DATA, normalizedReportInfoList);
		logger.info("Complted transforming the parsed data into normalized format. Normalized data list size - "+normalizedReportInfoList.size()); 
		
		return processOutput;
	}

	/**
	 * 
	 * @param normalizedReportInfoList
	 * @param closingBalance
	 * @param closingBalanceIndex
	 * @param listIndex
	 */
	private void updateClosingBalance(List<Map<CanonicalSGDLYHISTReportDataConstants, String>> normalizedReportInfoList, String closingBalance, int closingBalanceIndex, int listIndex) {
		for (int i = listIndex; i > listIndex - closingBalanceIndex; i--) {
			Map<CanonicalSGDLYHISTReportDataConstants, String> dataMap = normalizedReportInfoList.get(i-1);
			//Cleanup Closing Balance
			closingBalance = closingBalance.replaceAll(",", "");
			dataMap.put(CLOSING_BALANCE, closingBalance);
		}
		/*for (Iterator iterator = normalizedReportInfoList.iterator(); iterator.hasNext();) {
			Map<NormalizedSGDLYHISTReportDataConstants, String> map = (Map<NormalizedSGDLYHISTReportDataConstants, String>) iterator.next();
			logger.debug(map); 
		}*/
	}

	/**
	 * 
	 * @param normalizedReportInfoList
	 * @param dailyBalance
	 * @param dailyBalanceIndex
	 * @param listIndex
	 */
	private void updateDailyBalance(List<Map<CanonicalSGDLYHISTReportDataConstants, String>> normalizedReportInfoList, String dailyBalance, int dailyBalanceIndex, int listIndex) {
		
		for (int i = listIndex; i > listIndex - dailyBalanceIndex; i--) {
			Map<CanonicalSGDLYHISTReportDataConstants, String> dataMap = normalizedReportInfoList.get(i-1);
			dailyBalance = dailyBalance.replaceAll(",", "");
			dataMap.put(DAILY_BALANCE, dailyBalance);
		}
		/*for (Iterator iterator = normalizedReportInfoList.iterator(); iterator.hasNext();) {
			Map<NormalizedSGDLYHISTReportDataConstants, String> map = (Map<NormalizedSGDLYHISTReportDataConstants, String>) iterator.next();
			logger.debug(map); 
		}*/
		
	}


	/**
	 * 
	 * @param dataMap
	 * @return
	 */
	private boolean isPageHeader(Map<Enum<?>, String> dataMap) {
		for(FIRST_LINE firstLine : FIRST_LINE.values()) {
			if (!dataMap.containsKey(firstLine)) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 * @param dataMap
	 * @return
	 */
	private boolean isReportHeader(Map<Enum<?>, String> dataMap) {
		for(SGDLYHISTReportDataHeaderFormatConstants reportDataHeader : SGDLYHISTReportDataHeaderFormatConstants.values()) {
			if (!dataMap.containsKey(reportDataHeader)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param dataMap
	 * @return
	 */
	private boolean isAccountNumberData(Map<Enum<?>, String> dataMap) {
		for(ACCOUNT_NUMBER_LINE accountNumber : ACCOUNT_NUMBER_LINE.values()) {
			if(!dataMap.containsKey(accountNumber)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param dataMap
	 * @return
	 */
	private boolean isReportData(Map<Enum<?>, String> dataMap) {
		for(REPORT_DATA_LINE reportDataGLLine : REPORT_DATA_LINE.values()) {
			if(!dataMap.containsKey(reportDataGLLine)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param dataMap
	 * @return
	 */
	private boolean isOpeningBalance(Map<Enum<?>, String> dataMap) {
		for(OPENING_BALANCE_LINE openingBalLine : OPENING_BALANCE_LINE.values()) {
			if(!dataMap.containsKey(openingBalLine)) {
				return false;
			}
		} 
		return true;
	}
	
	/**
	 * 
	 * @param dataMap
	 * @return
	 */
	private boolean isDailyBalance(Map<Enum<?>, String> dataMap) {
		for(DAILY_BALANCE_LINE dailyBalLine : DAILY_BALANCE_LINE.values()) {
			if(!dataMap.containsKey(dailyBalLine)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param dataMap
	 * @return
	 */
	private boolean isClosingBalance(Map<Enum<?>, String> dataMap) {
		for(CLOSING_BALANCE_LINE closingBalLine : CLOSING_BALANCE_LINE.values()) {
			if(!dataMap.containsKey(closingBalLine)) {
				return false;
			}
		}
		return true;
	}

}
