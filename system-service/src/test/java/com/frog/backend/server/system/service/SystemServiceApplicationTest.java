package com.frog.backend.server.system.service;

import com.frog.backend.server.system.service.pojo.domain.UserInfo;
import com.frog.backend.server.system.service.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * Description
 *
 * @author yxy
 * @date 2021-01-27
 */
@Slf4j
@SpringBootTest
public class SystemServiceApplicationTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void test(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("ZhuFangYu");
        UserInfo rs = userInfoService.selectById("942eccb750534701a821a7b0efea6ac5");
        log.info("userInfo={}",userInfo);
        log.info("count={}",rs);
    }

}
