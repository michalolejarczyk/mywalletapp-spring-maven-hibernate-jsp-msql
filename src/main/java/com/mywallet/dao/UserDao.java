package com.mywallet.dao;

import java.util.List;

import com.mywallet.model.User;

public interface UserDao {
	User findById(Long id);

	User findByUsername(String username);

	void save(User user);

	void deleteByUsername(String username);

	List<User> findAllUsers();
}
