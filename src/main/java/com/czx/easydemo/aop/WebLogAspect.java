package com.czx.easydemo.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一日志处理切面
 */

@Aspect
@Component
@Slf4j
@Order(2)
public class WebLogAspect {

    @Pointcut("@annotation(com.czx.easydemo.aop.LogAop)")
    public void LogJust() {
    }

    @Around("LogJust()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        List<Object> logArgs = Arrays.stream(point.getArgs())
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        try {
            log.info("请求参数={}", JSON.toJSONString(logArgs));
        } catch (Exception e) {
            log.error("请求参数获取异常", e);
        }
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        try {
            log.info(" 返回结果={},请求耗时={}ms", JSON.toJSONString(result), time);
        } catch (Exception e) {
            log.error("返回参数获取异常", e);
        }
        return result;
    }
}