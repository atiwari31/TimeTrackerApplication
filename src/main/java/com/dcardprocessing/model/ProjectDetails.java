package com.dcardprocessing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDetails {
	
	private Long freelancer_id;
	private String token;
	private int Project_leads_id;

	public Long getFreelancer_id() {
		return freelancer_id;
	}

	public void setFreelancer_id(Long freelancer_id) {
		this.freelancer_id = freelancer_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the project_leads_id
	 */
	public int getProject_leads_id() {
		return Project_leads_id;
	}

	/**
	 * @param project_leads_id the project_leads_id to set
	 */
	public void setProject_leads_id(int project_leads_id) {
		Project_leads_id = project_leads_id;
	}

}
