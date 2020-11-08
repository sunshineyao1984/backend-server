package com.frog.backend.server.service.base.util;

import com.frog.backend.server.service.core.enums.CodeEnum;
import com.frog.backend.server.service.core.exception.ServiceException;

import java.util.Random;

/**
 * Description 随机数字串生成工具类
 *
 * @author yxy
 * @date 2020-10-13
 */
public class RandomUtils {

    private static final String NUMBERS = "0123456789";

    public static String randomNumbers(int n) throws ServiceException {
        if(n <= 0){
            throw new ServiceException(CodeEnum.ERROR,"随机数字串位数必须大于0！");
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i< n; i++){
            builder.append(randomNumber());
        }
        return builder.toString();
    }

    /**
     * 生成随机数字
     * @return
     */
    public static String randomNumber(){
        Random random = new Random();
        return NUMBERS.charAt(random.nextInt(NUMBERS.length()))+"";
    }

}
