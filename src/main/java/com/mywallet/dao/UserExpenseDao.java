package com.mywallet.dao;

import java.util.List;

import com.mywallet.model.User;
import com.mywallet.model.UserExpense;

public interface UserExpenseDao {
	UserExpense findById(Long id);

	void save(UserExpense userExpense);

	void deleteById(Long id);

	List<UserExpense> findAllUserExpensesByUser(User user);

}
