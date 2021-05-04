package com.frog.backend.server.system.service.api.impl;

import com.frog.backend.server.service.base.util.ResultUtils;
import com.frog.backend.server.service.core.enums.CodeEnum;
import com.frog.backend.server.service.core.pojo.vo.Result;
import com.frog.backend.server.system.service.api.UserService;
import com.frog.backend.server.system.service.api.pojo.vo.SysUserVo;
import com.frog.backend.server.system.service.pojo.domain.SysUser;
import com.frog.backend.server.system.service.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description
 *
 * @author yxy
 * @date 2021-05-04
 */
@DubboService(protocol = "dubbo")
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result<SysUserVo> getUserInfoByUsername(String username) {
        SysUser sysUserSelect = new SysUser();
        sysUserSelect.setUserName(username);
        SysUser sysUser = sysUserService.selectOne(sysUserSelect);
        if(sysUser == null){
            return ResultUtils.returnResultDynamic(CodeEnum.ERROR,"查无此用户", null);
        }
        SysUserVo sysUserVo = new SysUserVo();
        BeanUtils.copyProperties(sysUser,sysUserVo);
        return ResultUtils.returnResultSuccess(sysUserVo);
    }
}
