/*
#
# Copyright 2013 The Trustees of Indiana University
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# -----------------------------------------------------------------
#
# Project: HTRC-UI-AuditAnalyzer
# File:  DataAPIAccessRealTimeMonitorAction.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package edu.indiana.htrc.action.realtime;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import util.Utility;

import com.opensymphony.xwork2.ActionSupport;

import edu.indiana.htrc.global.HTRCLogConfig;

public class DataAPIAccessRealTimeMonitorAction extends ActionSupport{

	private InputStream inputStream;
	 
	/**
	 * get input stream
	 * @return input stream
	 */
    public InputStream getInputStream() {
        return inputStream;
    }
	//int line_count;
	//private String display;
	//private Map<String, Object> session;
	/*List<Double> access_rate_list; 
	
	public List<Double> getAccess_rate_list() {
		return access_rate_list;
	}

	public void setAccess_rate_list(List<Double> access_rate_list) {
		this.access_rate_list = access_rate_list;
	}*/

	/*public int getLine_count() {
		return line_count;
	}

	public void setLine_count(int line_count) {
		this.line_count = line_count;
	}
*/
	public String execute() {
	//	setDisplay("/pages/dynamicChart.jsp");
	//	double access_count = 0; // number of access/second
		//List<Double> access_rate_list = null;
		/*if(session.get("access_rate_list") != null){
			access_rate_list = (List<Double>) session.get("access_rate_list");
		}else{
			access_rate_list = new LinkedList<Double>();
		}*/
				
		//for (int i = 0; i < 3; i++) {

			String line = "wc -l "
					+ HTRCLogConfig.AUDIT_LOG_HOME + "data-api-audit.log";

			String result1 = Utility.exec(line);

			double new_line_count1 = Double.parseDouble(result1.split(" ")[0]);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			String result2 = Utility.exec(line);

			double new_line_count2 = Double.parseDouble(result2.split(" ")[0]);

			double access_count = new_line_count2 - new_line_count1 ;
			
			String result = access_count + "";
			
			// inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
			try {
				inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		    
			
			/*if(access_rate_list.size() > 80){
				access_rate_list.remove(0);
				access_rate_list.add((access_count/4));
			}else{
				access_rate_list.add((access_count/4));
			}
			
		session.put("access_rate_list", access_rate_list);*/
		
		return SUCCESS;
	}

	/*public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}*/
}
