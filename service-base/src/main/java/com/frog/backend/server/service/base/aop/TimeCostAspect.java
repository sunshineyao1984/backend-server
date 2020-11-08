package com.frog.backend.server.service.base.aop;

import com.frog.backend.server.service.core.pojo.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Description 接口耗时计算的切面
 *
 * @author yxy
 * @date 2020-10-12
 */
@Slf4j
@Aspect
@Component
public class TimeCostAspect {

    /**
     * 最大允许耗时时间
     */
    private static final long MAX_COST = 1000L;

    /**
     * 切面
     */
    private static final String POINT = "execution (* com.frog.backend..*.controller..*.*(..))";

    @Pointcut(POINT)
    public void performance() {
    }

    @Around("performance()")
    public Object watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        obj = joinPoint.proceed(args);
        if (!(obj instanceof BaseResult)) {
            return obj;
        }
        long endTime = System.currentTimeMillis();
        BaseResult baseResult = (BaseResult) obj;
        long cost = endTime - startTime;
        baseResult.setCost(cost);
        //慢接口打印日志
        if (cost >= MAX_COST) {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            log.warn("Request:{} cost:{}ms is too long!", request.getRequestURI(), cost);
        }
        return baseResult;
    }
}
