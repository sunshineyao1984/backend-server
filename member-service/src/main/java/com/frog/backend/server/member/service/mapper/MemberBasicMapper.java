package com.frog.backend.server.member.service.mapper;

import com.frog.backend.components.mysql.component.mapper.BaseMapper;
import com.frog.backend.server.member.service.pojo.domain.MemberBasic;
import org.springframework.stereotype.Repository;

/**
 * Description member_basic表Mapper
 * 
 * @author yxy
 * @date 2020/10/21
 */
@Repository
public interface MemberBasicMapper extends BaseMapper<MemberBasic> {
}