package com.mywallet.service;

import com.mywallet.model.UserProfile;

public interface UserProfileService {

	UserProfile findById(Long id);

	UserProfile findByType(String type);
}
