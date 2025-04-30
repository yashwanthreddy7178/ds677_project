package sk.ehr.learning.course.domain.entity;

import sk.ehr.learning.util.Entity;

public class Institute implements Entity {
	//
	private String name; 
	private String contactName; 
	private String contactNumber; 
	private String siteUrl;
	
	public Institute(String name) {
		// 
		this.name = name; 
	}
	
	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		builder.append("Name:").append(name); 
		builder.append(", contact name:").append(contactName); 
		builder.append(", contact number:").append(contactNumber); 
		builder.append(", site url:").append(siteUrl); 
		
		return builder.toString(); 
	}
	
	@Override
	public String getId() {
		return name; 
	}
	
	public String getIdFormat() {
		return "%s"; 
	}
	
	public static Institute getSample() {
		// 
		Institute sample = new Institute("KOSTA"); 
		sample.setContactName("Kim,Kildong");
		sample.setContactNumber("070-0100-8877");
		sample.setSiteUrl("www.kosta.or.kr"); 
		
		return sample; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
}
