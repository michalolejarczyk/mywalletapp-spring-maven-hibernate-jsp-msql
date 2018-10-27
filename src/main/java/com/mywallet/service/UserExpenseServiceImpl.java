package com.mywallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mywallet.dao.UserExpenseDao;
import com.mywallet.model.User;
import com.mywallet.model.UserExpense;

@Service("userExpenseService")
@Transactional
public class UserExpenseServiceImpl implements UserExpenseService {

	@Autowired
	private UserExpenseDao dao;

	@Override
	public UserExpense findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public void saveExpense(UserExpense userExpense) {
		dao.save(userExpense);
	}

	@Override
	public void updateUserExpense(UserExpense expense) {
		UserExpense entity = dao.findById(expense.getId());
		if (entity != null) {
			entity.setExpense(expense.getExpense());
			entity.setExpenseDate(expense.getExpenseDate());
			entity.setPurpose(expense.getPurpose());
		}

	}

	@Override
	public List<UserExpense> findAllUserExpensesByUser(User user) {
		return dao.findAllUserExpensesByUser(user);
	}

	@Override
	public void deleteUserExpenseById(Long id) {
		dao.deleteById(id);

	}

}
