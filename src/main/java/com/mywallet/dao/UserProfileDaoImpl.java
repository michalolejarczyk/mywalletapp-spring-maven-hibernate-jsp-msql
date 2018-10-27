package com.mywallet.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mywallet.model.UserProfile;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Long, UserProfile> implements UserProfileDao {

	@Override
	public UserProfile findById(Long id) {
		return getByKey(id);
	}

	@Override
	public UserProfile findByType(String type) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("type", type));
		return (UserProfile) criteria.uniqueResult();
	}

}
