package com.example.two.aspect;

import com.example.two.common.ContextConst;
import com.example.two.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(-1)// 保证该AOP在@Transactional之前执行
@Aspect
public class DynamicDataSourceAspect {

    @Before("execution(* com.example.two.service..*.*(..))")
    public void before(JoinPoint point){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.name());
    }

    @After("execution(* com.example.two.service..*.*(..))")
    public void after(JoinPoint point){
        DataSourceContextHolder.clearDataSource();
    }

}
