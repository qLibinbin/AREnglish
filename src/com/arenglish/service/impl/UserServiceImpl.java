package com.arenglish.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.arenglish.dao.UserDao;
import com.arenglish.domain.CheckPoint;
import com.arenglish.domain.User;
import com.arenglish.service.UserService;
import com.arenglish.utils.UUIDUtils;

public class UserServiceImpl implements UserService {
	private UserDao userDao;


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	public User login(User user) {
		if (user==null) {
			return null;
		}
		User userFromDao = userDao.getUserByAccount(user.getUserAccount());
		if (userFromDao==null ) {
			return null;
		}
		if (userFromDao.getUserPassword().equals(user.getUserPassword())) {
			return userFromDao;
		}
		
		return null;
	}


	public boolean saveNewUser(User user) {
		if (user==null || user.getUserAccount()==null || user.getUserPassword()==null) {
			return false;
		}
		user.setUserId(UUIDUtils.getUUID());
		if (userDao.saveUser(user)) {
			return true;
		}
		
		return false;
	}


	public User getUserById(String userId) {
		if (userId==null) {
			return null;
		}
		return userDao.getUserById(userId);
	}


	public boolean addPassingCheckpoint(String userId, Integer checkpointIndex) {
		if (userId==null || checkpointIndex==null) {
			return false;
		}
		if (userDao.addPassingCheckpoint(userId,checkpointIndex)) {
			return true;
		}
		return false;
	}


	public List<CheckPoint> getAllPassingCheckpointById(String userId) {
		if (userId==null) {
			return null;
		}
		Set<CheckPoint> checkPointList= userDao.getAllPassingCheckpointById(userId);
		List<CheckPoint> list = new ArrayList<CheckPoint>(checkPointList);
		Collections.sort(list);
		return list;
	}


	public Integer getCreditById(String userId) {
		Integer userCredit = this.getUserById(userId).getUserCredit();
		if (userCredit==null) {
			return 0;
		}
		return userCredit;
	}


	public boolean modifyCredit(String userId, boolean isIncrease, Integer creditCount) {
		if (userId==null || creditCount==null) {
			return false;
		}
		boolean flag = userDao.modifyCredit(userId,isIncrease,creditCount);
		return flag;
	}
	
	
}
