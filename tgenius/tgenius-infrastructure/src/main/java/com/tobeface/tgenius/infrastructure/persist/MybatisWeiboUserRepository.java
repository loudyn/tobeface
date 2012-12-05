package com.tobeface.tgenius.infrastructure.persist;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.tobeface.modules.helper.JsonHelper;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.WeiboUserRepository;

/**
 * 
 * @author loudyn
 * 
 */
@Repository
public class MybatisWeiboUserRepository extends SqlSessionDaoSupport implements WeiboUserRepository {

	@SuppressWarnings("unchecked")
	@Override
	public void save(WeiboUser entity) {
		Map<String, Object> entityAsMap = JsonHelper.newfor(entity, Map.class);
		String entityAsString = JsonHelper.toJsonString(entity);
		entityAsMap.put("json", entityAsString);
		getSqlSession().insert("com.tobeface.tgenius.domain.weiboUser.save", entityAsMap);
	}

}
