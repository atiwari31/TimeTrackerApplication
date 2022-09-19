package com.dcardprocessing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dcardprocessing.bean.ImageEntity;
@Service
public class ImageServiceImpl implements ImageService{
	
	 public int time_id_image;
	 public int lead_id_image;
	
	
//     @Autowired
//	private ImageRepository imageRepository;
     
//	@Override
//	public ImageEntity save(ImageEntity entity) {
//		// TODO Auto-generated method stub
//		imageRepository.save(entity);
//		return imageRepository.save(entity);
//	}

	@Override
	public ImageEntity update(ImageEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ImageEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(List<ImageEntity> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ImageEntity find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImageEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageEntity save(ImageEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the time_id_image
	 */
	public int getTime_id_image() {
		return time_id_image;
	}

	/**
	 * @param time_id_image the time_id_image to set
	 */
	public void setTime_id_image(int time_id_image) {
		this.time_id_image = time_id_image;
	}

	/**
	 * @return the lead_id_image
	 */
	public int getLead_id_image() {
		return lead_id_image;
	}

	/**
	 * @param lead_id_image the lead_id_image to set
	 */
	public void setLead_id_image(int lead_id_image) {
		this.lead_id_image = lead_id_image;
	}


}
