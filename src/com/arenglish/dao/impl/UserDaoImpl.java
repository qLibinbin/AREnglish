package com.arenglish.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jboss.jandex.TypeTarget.Usage;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.arenglish.dao.UserDao;
import com.arenglish.domain.CheckPoint;
import com.arenglish.domain.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{

	public boolean saveUser(User user) {
		Serializable save = getHibernateTemplate().save(user);
		if (save!=null) {
			//System.out.println(save.toString());
			return true;
		}
		return false;
	}

	public User getUserByAccount(final String userAccount) {
		
		User user=getHibernateTemplate().execute(new HibernateCallback<User>() {

			public User doInHibernate(Session session) throws HibernateException {
				String sql="select * from are_user where are_user_account = ?";
				SQLQuery query = session.createSQLQuery(sql)
						 .addEntity(User.class);
				query.setString(0, userAccount);
				User user = (User) query.uniqueResult();
				return user;
			}
			
		});
		
		return user;
	}

	public User getUserById(String userId) {
		User user = getHibernateTemplate().get(User.class, userId);
		return user;
	}

	public boolean addPassingCheckpoint(final String userId,final Integer checkpointIndex) {
		
		User user = getHibernateTemplate().get(User.class, userId);
		
		CheckPoint checkPoint=new CheckPoint();
		checkPoint.setCheckpointIndex(checkpointIndex);
		List<CheckPoint> checkPointList = getHibernateTemplate().findByExample(checkPoint);
		
		if (user.getCheckpointList().contains(checkPointList.get(0))) {
			return false;
			
		}else {
			user.getCheckpointList().add(checkPointList.get(0));
			getHibernateTemplate().saveOrUpdate(user);
		}
		
		
		return true;
	}

	public Set<CheckPoint> getAllPassingCheckpointById(String userId) {
		User user = getHibernateTemplate().get(User.class, userId);
		return user.getCheckpointList();
	}

	public boolean modifyCredit(String userId, boolean isIncrease, Integer creditCount) {
		try {
			User user = getHibernateTemplate().get(User.class, userId);
			if (user==null) {
				return false;
			}
			if (isIncrease) {
				user.setUserCredit(user.getUserCredit()+creditCount);
			}else if(user.getUserCredit() - creditCount >=0){
				user.setUserCredit(user.getUserCredit()-creditCount);
			}else {
				return false;
			}
			getHibernateTemplate().saveOrUpdate(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
