package com.frog.backend.server.system.service.service.impl;

import com.frog.backend.components.sqlserver.component.mapper.BaseMapper;
import com.frog.backend.components.sqlserver.component.service.impl.BaseServiceImpl;
import com.frog.backend.server.system.service.pojo.domain.UserInfo;
import com.frog.backend.server.system.service.service.UserInfoService;
import com.frog.backend.server.system.service.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * Description UserInfoServiceImpl
 *
 * @author yxy
 * @date 2021/01/27
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo,String> implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

    @Override
    protected BaseMapper<UserInfo> getMapper() {
        return userInfoMapper;
    }

}