package com.tobeface.tgenius.service;

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
