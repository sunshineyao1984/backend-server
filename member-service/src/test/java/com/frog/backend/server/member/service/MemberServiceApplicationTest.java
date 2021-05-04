package com.frog.backend.server.member.service;

import com.frog.backend.server.member.service.pojo.domain.MemberBasic;
import com.frog.backend.server.member.service.service.MemberBasicService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.ValueOperations;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Description
 *
 * @author yxy
 * @date 2020-10-11
 */
@Slf4j
@SpringBootTest
public class MemberServiceApplicationTest {

    @Autowired
    private MemberBasicService memberBasicService;

//    @Autowired
//    private ValueOperations<String,Object> valueOperations;

    @Test
    public void test(){
        MemberBasic memberBasic = new MemberBasic();
        memberBasic.setMobile("15320927785");
        memberBasic.setPassword("123456");
        memberBasic.setRealName("张思");
        memberBasic.setBirthday(LocalDate.of(1985,5,21));
        memberBasic.setCreateTime(LocalDateTime.now());
        int count = memberBasicService.insert(memberBasic);
        log.info("result={}",count);
    }

    @Test
    public void testRedis(){
//        valueOperations.set("frog","hello");
//        String va = (String) valueOperations.get("frog");
//        log.info("va={}",va);
    }
}
