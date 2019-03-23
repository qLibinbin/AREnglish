package com.arenglish.service;

import java.util.List;

import com.arenglish.domain.CheckPoint;
import com.arenglish.domain.User;

public interface UserService {

	boolean saveNewUser(User user);

	User login(User user);

	User getUserById(String userId);

	boolean addPassingCheckpoint(String userId, Integer checkpointIndex);

	List<CheckPoint> getAllPassingCheckpointById(String userId);

	Integer getCreditById(String userId);

	boolean modifyCredit(String userId, boolean isIncrease, Integer creditCount);

	boolean containAccount(String receiver);

}
