package com.tobeface.tgenius.infrastructure.persist;

import org.springframework.stereotype.Repository;

import com.tobeface.modules.orm.mybatis.MybatisRepositorySupport;
import com.tobeface.tgenius.domain.task.WeiboTask;
import com.tobeface.tgenius.domain.task.WeiboTaskRepository;

/**
 * 
 * @author loudyn
 * 
 */
@Repository
public class MybatisWeiboTaskRepository extends MybatisRepositorySupport<String, WeiboTask> implements WeiboTaskRepository {

	@Override
	public void save(WeiboTask entity) {
		super.save(entity);
		if (entity.hasUncommitHandleEvents()) {
			getSqlSession().insert(getNamespace().concat(".saveEvents"), entity);
		}

		entity.commitHandleEvents();
	}

	@Override
	public void update(WeiboTask entity) {
		if (entity.hasUncommitHandleEvents()) {
			getSqlSession().insert(getNamespace().concat(".saveEvents"), entity);
		}

		entity.commitHandleEvents();
	}
}
