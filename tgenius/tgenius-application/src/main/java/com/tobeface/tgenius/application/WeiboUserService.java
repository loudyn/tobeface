package com.tobeface.tgenius.application;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.domain.WeiboUser;

/**
 * 
 * @author loudyn
 *
 */
public interface WeiboUserService {

	Page<WeiboUser> queryPage(Page<WeiboUser> page);
}
