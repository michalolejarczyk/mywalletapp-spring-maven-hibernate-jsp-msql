package com.mywallet.dao;

import com.mywallet.model.UserProfile;

public interface UserProfileDao {
	public UserProfile findById(Long id);

	public UserProfile findByType(String type);
}
