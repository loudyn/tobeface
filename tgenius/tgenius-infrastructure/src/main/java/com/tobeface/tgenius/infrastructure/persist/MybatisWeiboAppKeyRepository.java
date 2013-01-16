package com.tobeface.tgenius.infrastructure.persist;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.modules.orm.DataAccessException;
import com.tobeface.modules.orm.mybatis.MybatisRepositorySupport;
import com.tobeface.tgenius.domain.WeiboPlatform;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;
import com.tobeface.tgenius.domain.appkey.WeiboAppKeyRepository;

/**
 * 
 * @author loudyn
 * 
 */
@Repository
public class MybatisWeiboAppKeyRepository extends MybatisRepositorySupport<String, WeiboAppKey> implements WeiboAppKeyRepository {

	@Override
	public void delete(String id) {
		Preconditions.hasText(id);
		try {

			getSqlSession().delete(getNamespace().concat(".deleteByApiKey"), id);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public List<WeiboAppKey> queryAll() {
		try {

			return super.query(new Object());
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public List<WeiboAppKey> queryByPlatform(WeiboPlatform platform) {

		try {

			return super.query(ImmutableMap.of("platform", platform));
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public List<WeiboAppKey> queryByPlatformAndEnablePrivateLetter(WeiboPlatform platform) {
		try {

			return super.query(ImmutableMap.of("platform", platform, "enablePrivateLetter", 1));
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
}
