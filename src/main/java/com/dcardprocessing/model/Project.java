package com.dcardprocessing.model;

public class Project {
private int projectId;
private String projectTitle;
private int projectLeadId;
/**
 * @return the projectId
 */
public int getProjectId() {
	return projectId;
}
/**
 * @param projectId the projectId to set
 */
public void setProjectId(int projectId) {
	this.projectId = projectId;
}
/**
 * @return the projectTitle
 */
public String getProjectTitle() {
	return projectTitle;
}
/**
 * @param projectTitle the projectTitle to set
 */
public void setProjectTitle(String projectTitle) {
	this.projectTitle = projectTitle;
}
/**
 * @return the projectLeadId
 */
public int getProjectLeadId() {
	return projectLeadId;
}
/**
 * @param projectLeadId the projectLeadId to set
 */
public void setProjectLeadId(int projectLeadId) {
	this.projectLeadId = projectLeadId;
}


}
