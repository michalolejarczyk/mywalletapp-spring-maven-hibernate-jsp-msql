package com.mywallet.service;

import java.util.List;

import com.mywallet.model.User;
import com.mywallet.model.UserExpense;

public interface UserExpenseService {
	void deleteUserExpenseById(Long id);

	UserExpense findById(Long id);

	void saveExpense(UserExpense userExpense);

	List<UserExpense> findAllUserExpensesByUser(User user);

	void updateUserExpense(UserExpense expense);
}
