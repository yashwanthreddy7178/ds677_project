package com.kovalyk.securemessages.form;

import java.io.Serializable;

public class SearchWrapper  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4706191315716721659L;
	
	private String searchInfo;

	public String getSearchInfo() {
		return searchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}
}
