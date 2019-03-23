package com.arenglish.test;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.arenglish.domain.CheckPoint;
import com.arenglish.domain.User;

public class Demo extends HibernateDaoSupport{
	@Test
	public void fun() {
		
		for(int i=0;i<10;++i) {
			int j=(int) ((Math.random()*9+1)*100000);
			System.out.println(j);
		}
	}
	@Test
	public void addPassingCheckpoint() {
		final String userId="bea75065ee7143278835ed1f49b7d615";
		final Integer checkpointIndex=1;
		Configuration configure = new Configuration().configure();
		SessionFactory sessionFactory=configure.buildSessionFactory();
		setSessionFactory(sessionFactory);
		
		
		List<Object> list = getHibernateTemplate().execute(new HibernateCallback<List<Object>>() {
	
			public List<Object> doInHibernate(Session session) throws HibernateException {
				String sql="select * from u.are_user left join c.are_checkpoint where u.are_user_id = ? and c.are_checkpoint_index=?";
				List<Object> list = session.createSQLQuery(sql)
						 .addEntity(User.class)
						 .addEntity(CheckPoint.class)
						 .setParameter(0, userId)
						 .setParameter(1, checkpointIndex).list();
				return list;
				
			}
		});
		System.out.println(list.size());
		if (list.size()<=0) {
			
			//getHibernateTemplate().
			
		}
		
		
		return ;
	}


}
