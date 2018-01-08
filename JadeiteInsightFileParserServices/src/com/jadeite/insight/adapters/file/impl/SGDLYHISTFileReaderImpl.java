/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.impl;

import static com.jadeite.insight.adapters.file.constants.CommonProcessorInputOutputConstants.SOURCE_FILE_DETAILS;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jadeite.insight.adapters.file.constants.CanonicalSGDLYHISTReportDataConstants;
import com.jadeite.insight.adapters.file.constants.CommonProcessorInputOutputConstants;
import com.jadeite.insight.adapters.file.processors.SGDLYHISTDataTransformerProcessor;
import com.jadeite.insight.adapters.file.processors.SGDLYHISTFileParserProcessor;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 9, 2017
 * 
 *
 */
public class SGDLYHISTFileReaderImpl {

	private static Logger logger = null;
	private static String applicationName = null;
	static {
		logger = LogManager.getLogger(SGDLYHISTFileReaderImpl.class);
	}

	
	public static void main(String[] args) throws Exception {
		
		Map<Object, Object> processorInputSGDLYHIST  = new HashMap<Object, Object>(10);
		Map<Object, Object> processorOutputSGDLYHIST = new HashMap<Object, Object>(10);
		
		String fileName = "C:\\Users\\s1807138\\Desktop\\SG_SG038RDR_RPT_HQ2JBU3P.dat";
		
		processorInputSGDLYHIST.put(SOURCE_FILE_DETAILS, fileName);
		
		SGDLYHISTFileParserProcessor sgdlyhistFileParserProcessor = new SGDLYHISTFileParserProcessor();
		processorOutputSGDLYHIST = sgdlyhistFileParserProcessor.process(processorInputSGDLYHIST);
		
		
		SGDLYHISTDataTransformerProcessor dataTransformerProcessor = new SGDLYHISTDataTransformerProcessor();
		processorOutputSGDLYHIST = dataTransformerProcessor.process(processorOutputSGDLYHIST);
		
		List<Map<CanonicalSGDLYHISTReportDataConstants, String>> normalizedReportInfoList = (List<Map<CanonicalSGDLYHISTReportDataConstants, String>>) processorOutputSGDLYHIST.get(CommonProcessorInputOutputConstants.CANONICAL_SGDLYHIST_DATA);
		
		FileWriter writer;
		writer = new FileWriter("C:\\Users\\s1807138\\Desktop\\SG_SG038RDR_RPT_HQ2JBU3P_CSV.csv", false);  //True = Append to file, false = Overwrite
		
		/*// Write CSV
		
		writer.write(CanonicalSGDLYHISTReportDataConstants.ROUTING_TRANSIT.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.PRINTED_DATE.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.ROUTING_TRANSIT_ADDRESS.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.REPORT_ID.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.PAGE_NUMBER.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.ROUTING_TRANSIT_REGION.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.CURRENCY.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.REPORT_MONTH.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.ACCOUNT_NUMBER.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.ACCOUNT_NAME.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.OPENING_BALANCE.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.TRANS_DATE.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.MNEMONIC.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.AMOUNT.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.NARRATIVE.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.LOC_SOURCE.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.DAILY_BALANCE.getKey());
		writer.write(",");
		writer.write(CanonicalSGDLYHISTReportDataConstants.CLOSING_BALANCE.getKey());
		writer.write("\r\n");
		
		for (int i = 0; i < normalizedReportInfoList.size(); i++) { 
			
			
			ROUTING_TRANSIT			("ROUTING_TRANSIT", String.class, 5, null, null, "00000", true), 
			PRINTED_DATE			("PRINTED_DATE", Timestamp.class, 20, null, null,"", false), 
			ROUTING_TRANSIT_ADDRESS	("ROUTING_TRANSIT_ADDRESS", String.class, 40, null, null, "", true), 
			REPORT_ID				("REPORT_ID", String.class, 20, null, null, "", false), 
			PAGE_NUMBER				("PAGE_NUMBER", Integer.class, 4, null, null, "", false), 
			ROUTING_TRANSIT_REGION	("ROUTING_TRANSIT_REGION", String.class, 40, null, null, "", true), 
			CURRENCY				("CURRENCY", String.class, 3, null, null,	"", true), 
			REPORT_MONTH			("REPORT_MONTH", String.class, 4, null, null, "", true),
			ACCOUNT_NUMBER			("ACCOUNT_NUMBER", Integer.class, 8, null, null, "", false), 
			ACCOUNT_NAME			("ACCOUNT_NAME", String.class, 124, null, null,"", false), 
			OPENING_BALANCE			("OPENING_BALANCE", Double.class, 38, null, null, "", false), 
			TRANS_DATE				("TRANS_DATE", Date.class, 13, null, null,"", false), 
			MNEMONIC				("MNEMONIC", String.class, 14, null, null, "", false), 
			AMOUNT					("AMOUNT", Double.class, 21, null, null,"", false), 
			NARRATIVE				("NARRATIVE", String.class, 11, null, null, "", false), 
			LOC_SOURCE				("LOC_SOURCE", String.class, 73, null,null, "", false), 
			DAILY_BALANCE			("DAILY_BALANCE", Double.class, 38, null, null,"", false), 
			CLOSING_BALANCE			("CLOSING_BALANCE", Double.class, 38, null, null, "", false);
			
			
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.ROUTING_TRANSIT).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.PRINTED_DATE).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.ROUTING_TRANSIT_ADDRESS).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.REPORT_ID).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.PAGE_NUMBER).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.ROUTING_TRANSIT_REGION).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.CURRENCY).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.REPORT_MONTH).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.ACCOUNT_NUMBER).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.ACCOUNT_NAME).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.OPENING_BALANCE).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.TRANS_DATE).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.MNEMONIC).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.AMOUNT).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.NARRATIVE).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.LOC_SOURCE).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.DAILY_BALANCE).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'"+normalizedReportInfoList.get(i).get(CanonicalSGDLYHISTReportDataConstants.CLOSING_BALANCE).toString().replaceAll(",", ""));
			writer.write("\r\n");
		}*/
		System.out.println("Write success!");
		writer.close();
		
		
		
	}
	
	
}
