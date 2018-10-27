package com.mywallet.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mywallet.model.PersistentLogin;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
		implements PersistentTokenRepository {

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
		persist(persistentLogin);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		PersistentLogin persistentLogin = getByKey(series);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed);
		update(persistentLogin);

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		try {
			Criteria criteria = createEntityCriteria();
			criteria.add(Restrictions.eq("series", seriesId));
			PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();

			return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
					persistentLogin.getToken(), persistentLogin.getLast_used());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void removeUserTokens(String username) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("username", username));
		PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();
		if (persistentLogin != null) {
			delete(persistentLogin);
		}
	}

}
