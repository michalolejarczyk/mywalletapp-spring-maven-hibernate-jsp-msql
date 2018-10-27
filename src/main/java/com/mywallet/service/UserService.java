package com.mywallet.service;

import java.util.List;

import com.mywallet.model.User;

public interface UserService {
	User findById(Long id);

	User findByUsername(String username);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserByUsername(String sso);

	List<User> findAllUsers();

	boolean isUserUsernameUnique(Long id, String sso);
}
