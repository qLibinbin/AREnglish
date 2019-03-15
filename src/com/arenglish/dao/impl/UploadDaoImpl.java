package com.arenglish.dao.impl;


import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.arenglish.dao.UploadDao;
import com.arenglish.domain.Image;

public class UploadDaoImpl extends HibernateDaoSupport implements UploadDao{

	public void saveImage(Image image) {
		
		getHibernateTemplate().save(image);
	}
	
}
