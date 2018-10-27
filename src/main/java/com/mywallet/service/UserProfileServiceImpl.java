package com.mywallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mywallet.dao.UserProfileDao;
import com.mywallet.model.UserProfile;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileDao dao;

	@Override
	public UserProfile findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public UserProfile findByType(String type) {
		return dao.findByType(type);
	}

}
