/**
 * ECPS Health Center
 */
package com.bns.ecps.hc.finmon.commands;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import com.bns.ecps.hc.finmon.constants.CanonicalSGDLYHISTReportDataConstants;
import com.bns.ecps.hc.finmon.constants.CommandContextConstants;

/**
 * @author Jay Mukherjee
 * 
 * @date Nov 23, 2017
 * 
 *
 */
public class SGDLYHISTConvertCSVCommand implements Command {

	@Override
	public boolean execute(Context commandContext) throws Exception {

		List<Map<CanonicalSGDLYHISTReportDataConstants, String>> canonicalSGDLYHISTReportData = (List<Map<CanonicalSGDLYHISTReportDataConstants, String>>) commandContext
				.get(CommandContextConstants.SG_DAILY_HISTORY_CANONICAL_DATA);

		FileWriter writer;
		writer = new FileWriter("C:\\ECPSHealthCenterWorkspace\\ECPSHealthCenterSpace\\MonitorOutput\\SG_SG038RDR_RPT_HQ2JBU3P_CSV.csv", false); // True =
																											// Append to
																											// file,
																											// false =
																											// Overwrite

		// Write CSV

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

		System.out.println("Normalized Data size - "+canonicalSGDLYHISTReportData.size());
		for (int i = 0; i < canonicalSGDLYHISTReportData.size(); i++) {

			/*
			 * ROUTING_TRANSIT ("ROUTING_TRANSIT", String.class, 5, null, null, "00000",
			 * true), PRINTED_DATE ("PRINTED_DATE", Timestamp.class, 20, null, null,"",
			 * false), ROUTING_TRANSIT_ADDRESS ("ROUTING_TRANSIT_ADDRESS", String.class, 40,
			 * null, null, "", true), REPORT_ID ("REPORT_ID", String.class, 20, null, null,
			 * "", false), PAGE_NUMBER ("PAGE_NUMBER", Integer.class, 4, null, null, "",
			 * false), ROUTING_TRANSIT_REGION ("ROUTING_TRANSIT_REGION", String.class, 40,
			 * null, null, "", true), CURRENCY ("CURRENCY", String.class, 3, null, null, "",
			 * true), REPORT_MONTH ("REPORT_MONTH", String.class, 4, null, null, "", true),
			 * ACCOUNT_NUMBER ("ACCOUNT_NUMBER", Integer.class, 8, null, null, "", false),
			 * ACCOUNT_NAME ("ACCOUNT_NAME", String.class, 124, null, null,"", false),
			 * OPENING_BALANCE ("OPENING_BALANCE", Double.class, 38, null, null, "", false),
			 * TRANS_DATE ("TRANS_DATE", Date.class, 13, null, null,"", false), MNEMONIC
			 * ("MNEMONIC", String.class, 14, null, null, "", false), AMOUNT ("AMOUNT",
			 * Double.class, 21, null, null,"", false), NARRATIVE ("NARRATIVE",
			 * String.class, 11, null, null, "", false), LOC_SOURCE ("LOC_SOURCE",
			 * String.class, 73, null,null, "", false), DAILY_BALANCE ("DAILY_BALANCE",
			 * Double.class, 38, null, null,"", false), CLOSING_BALANCE ("CLOSING_BALANCE",
			 * Double.class, 38, null, null, "", false);
			 */

			if (i > 750188) {
				System.out.println(i +" : "+canonicalSGDLYHISTReportData.get(i));   
			}
			
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.ROUTING_TRANSIT).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.PRINTED_DATE).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.ROUTING_TRANSIT_ADDRESS).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i).get(CanonicalSGDLYHISTReportDataConstants.REPORT_ID)
					.toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.PAGE_NUMBER).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.ROUTING_TRANSIT_REGION).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i).get(CanonicalSGDLYHISTReportDataConstants.CURRENCY)
					.toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.REPORT_MONTH).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.ACCOUNT_NUMBER).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.ACCOUNT_NAME).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.OPENING_BALANCE).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i).get(CanonicalSGDLYHISTReportDataConstants.TRANS_DATE)
					.toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i).get(CanonicalSGDLYHISTReportDataConstants.MNEMONIC)
					.toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i).get(CanonicalSGDLYHISTReportDataConstants.AMOUNT)
					.toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i).get(CanonicalSGDLYHISTReportDataConstants.NARRATIVE)
					.toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i).get(CanonicalSGDLYHISTReportDataConstants.LOC_SOURCE)
					.toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.DAILY_BALANCE).toString().replaceAll(",", ""));
			writer.write(",");
			writer.write("'" + canonicalSGDLYHISTReportData.get(i)
					.get(CanonicalSGDLYHISTReportDataConstants.CLOSING_BALANCE).toString().replaceAll(",", ""));
			writer.write("\r\n");

			//System.out.println("Line Number - "+i);
		}
		
		return false;

	}

}
