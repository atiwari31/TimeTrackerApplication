package com.dcardprocessing.bean;

//@Entity
//@Table(name="image")
public class ImageEntity {
//	 @Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private String image;

	 private String createdDate;
	 
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	 
}
