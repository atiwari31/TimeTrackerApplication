package com.dcardprocessing.bean;

//@Entity
//@Table(name="desktop")
public class DesktopEntity {
//	 @Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private String activity;
	 
	 private String createdDate;

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	 
}

