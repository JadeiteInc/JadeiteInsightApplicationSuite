/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.commands;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;

import com.jadeite.insight.adapters.file.constants.CanonicalSGDLYHISTReportDataConstants;
import com.jadeite.insight.adapters.file.constants.CommandContextConstants;
import com.jadeite.insight.adapters.file.utils.FinMonitorExcelUtils;

import static com.jadeite.insight.adapters.file.constants.CommonConstants.CA_23069;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.HA_95703;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.MO_83451;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.TO_65052;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.VA_93120;
import static com.jadeite.insight.adapters.file.constants.CommonConstants.WI_05827;

/**
 * @author Jay Mukherjee
 * 
 * @date Nov 24, 2017
 * 
 *
 */
public class SGDLYHISTSummaryExcelUpdaterCommand implements Command {

	private static Logger logger 						= null;
	private static String SGDLYHISTTabName 				= "SG_DAILY_HISTORY";
	private static int ONE_PASS_EXCEL_WRITE_THRESHOLD 	= 100000;
	
	static {
		logger = LogManager.getLogger(FinMonitorExcelInitiatorCommand.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.commons.chain.Command#execute(org.apache.commons.chain.Context)
	 */
	@Override
	public boolean execute(Context commandContext) throws Exception {

		String finMonitorSummaryExcelPath = commandContext.get(CommandContextConstants.FIN_MONITOR_SUMMARY_EXCEL_PATH).toString();
		@SuppressWarnings("unchecked")
		List<Map<CanonicalSGDLYHISTReportDataConstants, String>> canonicalSGDLYHISTReportData = (List<Map<CanonicalSGDLYHISTReportDataConstants, String>>) commandContext.get(CommandContextConstants.SG_DAILY_HISTORY_CANONICAL_DATA);
		logger.debug("The number of items on the Canonical List Map -"+canonicalSGDLYHISTReportData.size()); 
		
		@SuppressWarnings("unchecked")
		List<Map<CanonicalSGDLYHISTReportDataConstants, String>> canonicalSGDLYHISTCIDCReportData = (List<Map<CanonicalSGDLYHISTReportDataConstants, String>>) commandContext.get(CommandContextConstants.SG_DAILY_HISTORY_CANONICAL_CIDC_DATA);
		
		//Write the data
		SGDLYHISTSummaryExcelUpdaterCommand.writeSGDLYHISTDataToExcel(finMonitorSummaryExcelPath, canonicalSGDLYHISTCIDCReportData, true, 0, SGDLYHISTTabName);
				
		/*List<List<Map<CanonicalSGDLYHISTReportDataConstants, String>>> listOfSplitCanonicalSGDLYHISTReportData = 
				new ArrayList<List<Map<CanonicalSGDLYHISTReportDataConstants, String>>>(canonicalSGDLYHISTReportData.size()/ONE_PASS_EXCEL_WRITE_THRESHOLD);
		List<Map<CanonicalSGDLYHISTReportDataConstants, String>> tempCanonicalSGDLYHISTReportData = null;
		int thresholdCounter = 0;
		for (int index = 0; index < canonicalSGDLYHISTReportData.size(); index++) {
			if(index == 0) {
				if(!(canonicalSGDLYHISTReportData.size() > ONE_PASS_EXCEL_WRITE_THRESHOLD)) {
					listOfSplitCanonicalSGDLYHISTReportData.add(canonicalSGDLYHISTReportData);
					break;
				}
				tempCanonicalSGDLYHISTReportData = new ArrayList<Map<CanonicalSGDLYHISTReportDataConstants, String>>(ONE_PASS_EXCEL_WRITE_THRESHOLD);
			} 
			
			tempCanonicalSGDLYHISTReportData.add(canonicalSGDLYHISTReportData.get(index));
			thresholdCounter++;
			
			if(thresholdCounter == ONE_PASS_EXCEL_WRITE_THRESHOLD) {
				listOfSplitCanonicalSGDLYHISTReportData.add(tempCanonicalSGDLYHISTReportData);
				tempCanonicalSGDLYHISTReportData = new ArrayList<Map<CanonicalSGDLYHISTReportDataConstants, String>>(ONE_PASS_EXCEL_WRITE_THRESHOLD);
				thresholdCounter = 0;
			}
		}
		
		boolean isFirstSetOfData = Boolean.TRUE;
		int rowPositionCounter = 0;
		int writerCounter = 1;
		for (Iterator<List<Map<CanonicalSGDLYHISTReportDataConstants, String>>> iterator = listOfSplitCanonicalSGDLYHISTReportData.iterator(); iterator.hasNext();) {
			
			
			
			List<Map<CanonicalSGDLYHISTReportDataConstants, String>> tempList = (List<Map<CanonicalSGDLYHISTReportDataConstants, String>>) iterator.next();
			logger.debug("***Trying to write the excel for Set - "+writerCounter+" from postion - "+ rowPositionCounter+" ; "+tempList.size()+" rows.");
			System.out.println(tempList.get(0).get(CanonicalSGDLYHISTReportDataConstants.AMOUNT));
			System.out.println(tempList.get(tempList.size()-1).get(CanonicalSGDLYHISTReportDataConstants.AMOUNT));
			SGDLYHISTSummaryExcelUpdaterCommand.writeSGDLYHISTDataToExcel(finMonitorSummaryExcelPath, tempList, isFirstSetOfData, rowPositionCounter);
			logger.debug("***Completed writing the excel for Set - "+writerCounter+" from postion - "+ rowPositionCounter+" ; "+tempList.size()+" rows.");
			rowPositionCounter = ++rowPositionCounter + tempList.size();
			if (isFirstSetOfData) isFirstSetOfData = Boolean.FALSE;
			writerCounter++;
			if (writerCounter > 2) {
				break;
			}
		}*/
		
		return false;
	}

	/**
	 * 
	 * @param filePath
	 * @param canonicalSGDLYHISTReportData
	 * @throws Exception 
	 */
	private static void writeSGDLYHISTDataToExcel(String filePath, List<Map<CanonicalSGDLYHISTReportDataConstants, String>> canonicalSGDLYHISTReportData, boolean isFirstSetOfData, int rowPositionCounter, String tabName) throws Exception {

		logger.debug("Preparing to write the set of data of - "+ canonicalSGDLYHISTReportData.size() + " for the isFirstSetOfData -"+isFirstSetOfData);
		try {
			// Using InputStream to read the Excel file.
			InputStream excelFileToRead = new FileInputStream(filePath);
			// Converting the InputStream to XLSX Workbook Object
			XSSFWorkbook summaryExcelWB = new XSSFWorkbook(excelFileToRead);

			// Getting the SGDLYHIST Data Tab by name
			XSSFSheet sgDlyHistDataWS = summaryExcelWB.getSheet(tabName);
			if (sgDlyHistDataWS == null) {
				sgDlyHistDataWS = summaryExcelWB.createSheet(tabName);
			}

			//Get all the Tables on the Worksheet and search for "SGDLYHIST" Table.
			List<XSSFTable> sgDlyHistDataTableList = sgDlyHistDataWS.getTables();
			XSSFTable sgDlyHistTable = null;
			for (Iterator<XSSFTable> iterator = sgDlyHistDataTableList.iterator(); iterator.hasNext();) {
				XSSFTable xssfTable = (XSSFTable) iterator.next();
				if(xssfTable.getName().equals(tabName)) {
					sgDlyHistTable = xssfTable;
					break;
				} 
			}
			//Table does not exist, Hence creating the new Table.
			if (sgDlyHistTable == null) {
				sgDlyHistTable = sgDlyHistDataWS.createTable();
			}
			
			// Get the CT Table and pass that to the Util Method to have a Common Style
			CTTable sgDlyHistCTTable = sgDlyHistTable.getCTTable();
			sgDlyHistCTTable.setName(tabName); 
			FinMonitorExcelUtils.formatExcelTableWithHeader(sgDlyHistCTTable, tabName, CanonicalSGDLYHISTReportDataConstants.values(), canonicalSGDLYHISTReportData.size(), rowPositionCounter, isFirstSetOfData);
			XSSFRow row = null;
			XSSFCell localXSSFCell = null;
			/* Add remaining Table Data */
			
			for (int i = rowPositionCounter; i <= rowPositionCounter + canonicalSGDLYHISTReportData.size(); i++) // we have to populate 4 rows
			{
				/* Create a Row */
				row = sgDlyHistDataWS.createRow(i);
				
				int j = 0;
				for(CanonicalSGDLYHISTReportDataConstants canonicalSGDLYHISTReportDataConstant : CanonicalSGDLYHISTReportDataConstants.values()) {
					localXSSFCell = row.createCell(j);
					
					if (i-rowPositionCounter == 0 && isFirstSetOfData) {
						localXSSFCell.setCellValue(((CanonicalSGDLYHISTReportDataConstants)CanonicalSGDLYHISTReportDataConstants.values()[j]).getKey());
					} else if(i-rowPositionCounter == 0 && !isFirstSetOfData){
						writeCellWithDataType(localXSSFCell, canonicalSGDLYHISTReportData.get(i-rowPositionCounter).get(canonicalSGDLYHISTReportDataConstant), canonicalSGDLYHISTReportDataConstant.getDatatype()); 
					} else {
						writeCellWithDataType(localXSSFCell, canonicalSGDLYHISTReportData.get(i-rowPositionCounter-1).get(canonicalSGDLYHISTReportDataConstant), canonicalSGDLYHISTReportDataConstant.getDatatype()); 
					}
					j++;
				}
				localXSSFCell = null;
				row = null;
			}
			
			//Auto Resize the columns.
			for (int i = 0; i < CanonicalSGDLYHISTReportDataConstants.values().length; i++) {
				sgDlyHistDataWS.autoSizeColumn(i);
			}
			
			//Set Auto Filter - TRUE
			
			
			
		    //Write the file to File System
			FileOutputStream fileOut = new FileOutputStream(filePath);
			summaryExcelWB.write(fileOut);
			summaryExcelWB.close();
			fileOut.close();
			
			System.gc();
			
			logger.debug("The thread is going for sleep.");
			Thread.sleep(5000);
			
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
		logger.debug("Completed to write the set of data of - "+canonicalSGDLYHISTReportData.size() + " for the isFirstSetOfData -"+isFirstSetOfData);
  }
	
	
	private static void writeCellWithDataType(XSSFCell cell, Object value, Class<?> dataClass) throws Exception {
		if(String.class.getName().equals(dataClass.getName())) {
			String cellValue = String.valueOf(value);
			cell.setCellValue(cellValue);
		} else if(Double.class.getName().equals(dataClass.getName())) {
			Double cellValue = Double.valueOf(value.toString());
			cell.setCellValue(cellValue);
		} else if (Integer.class.getName().equals(dataClass.getName())) {
			Integer cellValue = Integer.valueOf(value.toString());
			cell.setCellValue(cellValue);
		} else {
			cell.setCellValue(value.toString());
		}
	}
}
