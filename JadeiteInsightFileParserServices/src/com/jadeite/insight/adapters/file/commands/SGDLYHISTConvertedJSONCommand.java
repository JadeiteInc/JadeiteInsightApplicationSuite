/**
 * ECPS Health Center
 */
package com.jadeite.insight.adapters.file.commands;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jadeite.insight.adapters.file.constants.CanonicalSGDLYHISTReportDataConstants;
import com.jadeite.insight.adapters.file.constants.CommandContextConstants;

/**
 * @author  Jay Mukherjee
 * 
 * @date    Nov 22, 2017
 * 
 *
 */
public class SGDLYHISTConvertedJSONCommand implements Command {

	private static Logger logger = null;
	static {
		logger = LogManager.getLogger(SGDLYHISTConvertedJSONCommand.class);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.commons.chain.Command#execute(org.apache.commons.chain.Context)
	 */
	@Override
	public boolean execute(Context commandContext) throws Exception {
		
		List<Map<CanonicalSGDLYHISTReportDataConstants, String>> canonicalSGDLYHISTReportData = 
				(List<Map<CanonicalSGDLYHISTReportDataConstants, String>>) commandContext.get(CommandContextConstants.SG_DAILY_HISTORY_CANONICAL_DATA);
		
		SGDLYHISTConvertedJSONCommand.listmap_to_json_string(canonicalSGDLYHISTReportData);
		
		return false;
	}

	/**
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static String listmap_to_json_string(List<Map<CanonicalSGDLYHISTReportDataConstants, String>> canonicalSGDLYHISTReportData) throws Exception
	{       
	    JSONArray json_arr=new JSONArray();
	    for (Map<CanonicalSGDLYHISTReportDataConstants, String> map : canonicalSGDLYHISTReportData) {
	        JSONObject json_obj=new JSONObject();
	        for (Map.Entry<CanonicalSGDLYHISTReportDataConstants, String> entry : map.entrySet()) {
	            String key = entry.getKey().getKey();
	            Object value = entry.getValue();
	            try {
	                json_obj.put(key,value);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }                           
	        }
	        json_arr.add(json_obj);
	    }
	    
	    try (FileWriter file = new FileWriter("C:\\Users\\s1807138\\Desktop\\SG_SG038RDR_RPT_HQ2JBU3P_json.json", false)) {
	    	for (int i = 0; i < json_arr.size(); i++) {
				String array_element = json_arr.get(i).toString();
				file.write(array_element);
			}			
			logger.info("Successfully Copied JSON Object to File...");
		}
	    
	    return "done";
	}
}
