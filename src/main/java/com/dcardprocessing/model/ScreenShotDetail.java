package com.dcardprocessing.model;

public class ScreenShotDetail {
	
	private Long freelancer_id;
	private String token;
	private int module_id;
	private byte [] image ;
	private int time_id;
	private int lead_id;
	/**
	 * @return the freelancer_id
	 */
	public Long getFreelancer_id() {
		return freelancer_id;
	}
	/**
	 * @param freelancer_id the freelancer_id to set
	 */
	public void setFreelancer_id(Long freelancer_id) {
		this.freelancer_id = freelancer_id;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the module_id
	 */
	public int getModule_id() {
		return module_id;
	}
	/**
	 * @param module_id the module_id to set
	 */
	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}
	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setimage(byte[] image) {
		this.image = image;
	}
	/**
	 * @return the time_id
	 */
	public int getTime_id() {
		return time_id;
	}
	/**
	 * @param time_id the time_id to set
	 */
	public void setTime_id(int time_id) {
		this.time_id = time_id;
	}
	/**
	 * @return the lead_id
	 */
	public int getLead_id() {
		return lead_id;
	}
	/**
	 * @param lead_id the lead_id to set
	 */
	public void setLead_id(int lead_id) {
		this.lead_id = lead_id;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}



	
}
