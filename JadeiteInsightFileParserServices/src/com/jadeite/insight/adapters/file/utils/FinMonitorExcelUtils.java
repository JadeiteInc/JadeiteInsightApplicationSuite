/**
 * ECPS Health Center
 */
package com.bns.ecps.hc.finmon.utils;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;

import com.bns.ecps.hc.finmon.constants.ExcelConstants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 24, 2017
 * 
 *
 */
public class FinMonitorExcelUtils {

	private static Logger logger = null;

	static {
		logger = LogManager.getLogger(FinMonitorExcelUtils.class);
	}
	
	public static void formatExcelTableWithHeader(CTTable ctTable, String tableName, Enum<?>[] headerDetails, int numOfRows, int startRow, boolean isFirstSetOfData) {
			   
	    /* Let us define the required Style for the table */    
	    CTTableStyleInfo tableStyle = ctTable.getTableStyleInfo();
	    if(tableStyle == null) {
	    	tableStyle = ctTable.addNewTableStyleInfo();
	    } 
	    
	    //Update the Table Style
	    tableStyle.setName(ExcelConstants.TABLE_STYLE_MEDIUM_4.getKey());
    	tableStyle.setShowColumnStripes(false);
    	tableStyle.setShowRowStripes(true);
    	tableStyle.setShowFirstColumn(false);
    	tableStyle.setShowLastColumn(false);
    	
//	    /* Define the data range including headers */
	    AreaReference dataRange = new AreaReference(new CellReference(0, 0), new CellReference(startRow + numOfRows, headerDetails.length-1));
//	    System.out.println(dataRange.toString());
	    
	    
//	    /* Set Range to the Table */
	    String dataRangeStr = dataRange.toString();
	    ctTable.setRef(dataRangeStr.substring(dataRangeStr.indexOf("[")+1,dataRangeStr.indexOf("]")));  
	    ctTable.setDisplayName(tableName);      /* this is the display name of the table */
	    ctTable.setName(tableName);    			/* This maps to "displayName" attribute in <table>, OOXML */            
	    ctTable.setId(1); 						//id attribute against table as long value
	    
	    if (isFirstSetOfData) {
	    	CTTableColumns headerColumns 		 = ctTable.getTableColumns();
	    	if (headerColumns == null) {
	    		headerColumns = ctTable.addNewTableColumns();
			} 
		    List<CTTableColumn> headerColumnList = headerColumns.getTableColumnList();
		    CTTableColumn ctTableColumn 		 = null;
		    for (int i = 0; i < headerDetails.length; i++) {
		    	if(i < headerColumns.getCount()) {
		    		ctTableColumn = headerColumnList.get(i);	    	
		    	} else {
		    		ctTableColumn = headerColumns.addNewTableColumn();
		    	} 
		    	ctTableColumn.setId(i+1);
//		    	ctTableColumn.setName(((Enum)headerDetails[i]).valueOf(headerDetails[i].getClass(), headerDetails[i].toString()).toString());
			}
		}
	}
	
}
