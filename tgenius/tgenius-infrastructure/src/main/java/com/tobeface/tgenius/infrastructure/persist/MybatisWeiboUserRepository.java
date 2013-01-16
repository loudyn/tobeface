package com.tobeface.tgenius.infrastructure.persist;

import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;
import com.tobeface.modules.helper.JsonHelper;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.modules.orm.DataAccessException;
import com.tobeface.modules.orm.mybatis.MybatisRepositorySupport;
import com.tobeface.tgenius.domain.WeiboPlatform;
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

			String entityAsString = JsonHelper.toJsonString(entity);
			getSqlSession().insert(getNamespace().concat(".save"), ImmutableMap.of("entity", entity, "json", entityAsString));
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public boolean existsByPlatformAndName(WeiboPlatform platform, String name) {
		Preconditions.notNull(platform);
		Preconditions.hasText(name);

		try {

			
			Object obj =  getSqlSession().selectOne(
													getNamespace().concat(".existsByPlatformAndName"),
													ImmutableMap.of("platform", platform, "name", name)
									);
			return null != obj;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void deleteByPlatformAndName(WeiboPlatform platform, String name) {
		Preconditions.notNull(platform);
		Preconditions.hasText(name);

		try {

			getSqlSession().delete(
									getNamespace().concat(".deleteByPlatformAndName"),
									ImmutableMap.of("platform", platform, "name", name)
							);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void update(WeiboUser entity) {

		Preconditions.notNull(entity);
		try {

			String entityAsString = JsonHelper.toJsonString(entity);
			getSqlSession().update(getNamespace().concat(".update"), ImmutableMap.of("entity", entity, "json", entityAsString));
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
}
