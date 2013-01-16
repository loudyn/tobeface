package com.tobeface.tgenius.infrastructure.persist;

import org.springframework.stereotype.Repository;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.modules.orm.DataAccessException;
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

		Preconditions.notNull(entity);
		try {

			super.save(entity);
			if (entity.hasUncommitHandleEvents()) {
				getSqlSession().insert(getNamespace().concat(".saveEvents"), entity);
			}

			entity.commitHandleEvents();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void update(WeiboTask entity) {

		Preconditions.notNull(entity);
		try {

			if (entity.hasUncommitHandleEvents()) {
				getSqlSession().insert(getNamespace().concat(".saveEvents"), entity);
			}

			entity.commitHandleEvents();
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
}
