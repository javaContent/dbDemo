package com.example.two.aspect;

import com.example.two.annotation.DataSourceSign;
import com.example.two.common.ContextConst;
import com.example.two.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Order(-1)// 保证该AOP在@Transactional之前执行
@Aspect
public class DynamicDataSourceAspect {

    @Before("execution(* com.example.two.service..*.*(..))")
    public void before(JoinPoint point){
//        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SLAVE.name());
        try {
            DataSourceSign annotationOfClass =
                    point.getTarget().getClass().getAnnotation(DataSourceSign.class);

            String methodName = point.getSignature().getName();

            Class[] parameterTypes =
                    ((MethodSignature) point.getSignature()).getParameterTypes();

            Method method =
                    point.getTarget().getClass().getMethod(methodName, parameterTypes);

            DataSourceSign methodAnnotation =
                    method.getAnnotation(DataSourceSign.class);

            methodAnnotation = methodAnnotation ==
                    null ? annotationOfClass:methodAnnotation;

            ContextConst.DataSourceType dataSourceType =
                    methodAnnotation != null &&  methodAnnotation.value() !=null ?
                            methodAnnotation.value() :ContextConst.DataSourceType.PRIMARY ;
            DataSourceContextHolder.setDataSource(dataSourceType.name());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.two.service..*.*(..))")
    public void after(JoinPoint point){
        DataSourceContextHolder.clearDataSource();
    }

}
