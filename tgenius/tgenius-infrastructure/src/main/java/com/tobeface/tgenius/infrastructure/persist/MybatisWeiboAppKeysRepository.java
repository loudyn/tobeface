package com.tobeface.tgenius.infrastructure.persist;

import org.springframework.stereotype.Repository;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.modules.orm.DataAccessException;
import com.tobeface.modules.orm.mybatis.MybatisRepositorySupport;
import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboAppKeysRepository;

/**
 * 
 * @author loudyn
 * 
 */
@Repository
public class MybatisWeiboAppKeysRepository extends MybatisRepositorySupport<String, WeiboAppKeys> implements WeiboAppKeysRepository {

	@Override
	public WeiboAppKeys findAnyAvaliable() {
		return null;
	}

	@Override
	public void delete(String id) {
		Preconditions.hasText(id);
		try {

			getSqlSession().delete(getNamespace().concat("deleteById"), id);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
}
