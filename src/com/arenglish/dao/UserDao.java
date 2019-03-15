package com.arenglish.dao;

import java.util.Set;

import com.arenglish.domain.CheckPoint;
import com.arenglish.domain.User;

public interface UserDao {

	boolean saveUser(User user);

	User getUserByAccount(String userAccount);

	User getUserById(String userId);

	boolean addPassingCheckpoint(String userId, Integer checkpointIndex);

	Set<CheckPoint> getAllPassingCheckpointById(String userId);

	boolean modifyCredit(String userId, boolean isIncrease, Integer creditCount);

}
