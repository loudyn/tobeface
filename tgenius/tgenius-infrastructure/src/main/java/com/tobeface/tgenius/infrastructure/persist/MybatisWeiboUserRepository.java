package com.tobeface.tgenius.infrastructure.persist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tobeface.modules.helper.JsonHelper;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.modules.orm.DataAccessException;
import com.tobeface.modules.orm.mybatis.MybatisRepositorySupport;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.WeiboUserRepository;

/**
 * 
 * @author loudyn
 * 
 */
@Repository
public class MybatisWeiboUserRepository extends MybatisRepositorySupport<String, WeiboUser> implements WeiboUserRepository {

	@Override
	public void save(WeiboUser entity) {
		Preconditions.notNull(entity);
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("entity", entity);
			String entityAsString = JsonHelper.toJsonString(entity);
			paramMap.put("json", entityAsString);
			getSqlSession().insert(getNamespace().concat(".save"), paramMap);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public WeiboUser queryUniqueByName(String name) {
		Preconditions.hasText(name);
		try {

			Map<String, String> jsonMap = (Map<String, String>) getSqlSession()
					.selectOne(getNamespace().concat(".queryUniqueByName"), name);
			return JsonHelper.fromJsonString(jsonMap.get("json"), WeiboUser.class);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void deleteByName(String name) {
		Preconditions.hasText(name);
		try {

			getSqlSession().delete(getNamespace().concat(".deleteByName"), name);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public boolean existsByName(String name) {
		try {

			Object result = getSqlSession().selectOne(getNamespace().concat(".existsByName"), name);
			return null != result;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void update(WeiboUser entity) {
		Preconditions.notNull(entity);
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("entity", entity);
			String entityAsString = JsonHelper.toJsonString(entity);
			paramMap.put("json", entityAsString);
			getSqlSession().update(getNamespace().concat(".update"), paramMap);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
}
