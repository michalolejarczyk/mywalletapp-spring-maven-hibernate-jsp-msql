package com.mywallet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mywallet.model.User;
import com.mywallet.model.UserExpense;

@Repository("userExpenseDao")
public class UserExpenseDaoImpl extends AbstractDao<Long, UserExpense> implements UserExpenseDao {

	@Override
	public UserExpense findById(Long id) {
		return getByKey(id);
	}

	@Override
	public void deleteById(Long id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		UserExpense userExpense = (UserExpense) criteria.uniqueResult();
		delete(userExpense);
	}

	@Override
	public void save(UserExpense userExpense) {
		persist(userExpense);

	}

	@Override
	public List<UserExpense> findAllUserExpensesByUser(User user) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("user", user));
		return (List<UserExpense>) criteria.list();
	}

}
