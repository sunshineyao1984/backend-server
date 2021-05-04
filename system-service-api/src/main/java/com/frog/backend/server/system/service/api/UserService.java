package com.frog.backend.server.system.service.api;

import com.frog.backend.server.service.core.pojo.vo.Result;
import com.frog.backend.server.system.service.api.pojo.vo.SysUserVo;

/**
 * Description 系统用户暴露Dubbo接口
 *
 * @author yxy
 * @date 2021-05-04
 */
public interface UserService {

    /**
     * 根据用户名称获取用户信息
     * @param username
     * @return
     */
    Result<SysUserVo> getUserInfoByUsername(String username);
}
