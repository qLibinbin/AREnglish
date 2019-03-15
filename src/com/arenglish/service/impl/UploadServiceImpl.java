package com.arenglish.service.impl;

import com.arenglish.dao.UploadDao;
import com.arenglish.domain.Image;
import com.arenglish.service.UploadService;

public class UploadServiceImpl implements UploadService{
	private UploadDao uploadDao;
	
	public void saveImage(Image image) {
		uploadDao.saveImage(image);
		
	}

	public void setUploadDao(UploadDao uploadDao) {
		this.uploadDao = uploadDao;
	}
	
}
