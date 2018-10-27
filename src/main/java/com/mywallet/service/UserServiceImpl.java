package com.mywallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mywallet.dao.UserDao;
import com.mywallet.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	@Override
	public User findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setUsername(user.getUsername());
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
			entity.setUserExpenses(user.getUserExpenses());
		}
	}

	@Override
	public void deleteUserByUsername(String username) {
		dao.deleteByUsername(username);
	}

	@Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	@Override
	public boolean isUserUsernameUnique(Long id, String sso) {
		User user = findByUsername(sso);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

}
