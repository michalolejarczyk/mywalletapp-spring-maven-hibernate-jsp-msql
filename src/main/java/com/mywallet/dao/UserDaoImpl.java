package com.mywallet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mywallet.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	@Override
	public void save(User user) {
		persist(user);
	}

	@Override
	public User findById(Long id) {
		User user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@Override
	public User findByUsername(String username) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("username", username));
		User user = (User) criteria.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@Override
	public void deleteByUsername(String username) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("username", username));
		User user = (User) criteria.uniqueResult();
		delete(user);
	}

	@Override
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		List<User> users = (List<User>) criteria.list();

		return users;
	}

}
