package com.dcardprocessing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcardprocessing.bean.ImageEntity;
import com.dcardprocessing.repository.ImageRepository;
@Service
public class ImageServiceImpl implements ImageService{
     @Autowired
	private ImageRepository imageRepository;
     
	@Override
	public ImageEntity save(ImageEntity entity) {
		// TODO Auto-generated method stub
		imageRepository.save(entity);
		return imageRepository.save(entity);
	}

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


}
