package com.frog.backend.server.system.service.mapper;

import com.frog.backend.components.sqlserver.component.mapper.BaseMapper;
import com.frog.backend.server.system.service.pojo.domain.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * Description user_info表Mapper
 * 
 * @author yxy
 * @date 2021/01/27
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}