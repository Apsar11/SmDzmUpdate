package com.resource;

import java.io.Serializable;

public class Message {//implements Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private String result;
	private String uniqueId;
	private String sm;
	private String dzm;
	// private String date;
	/*
	 * // Default constructor public Message() {}
	 * 
	 * // Constructor with parameters public Message(String result, String sm,
	 * String dzm) { this.result = result; // this.uniqueId = uniqueId; this.sm =
	 * sm; this.dzm = dzm; }
	 */

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	// @XmlElement
	 public String getUniqueId() { return uniqueId; }
	  
	  public void setUniqueId(String uniqueId) { this.uniqueId = uniqueId; }
	 

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getDzm() {
		return dzm;
	}

	public void setDzm(String dzm) {
		this.dzm = dzm;
	}

	/*
	 * public String getDate() { return date; }
	 * 
	 * public void setDate(String date) { this.date = date; }
	 */
}
