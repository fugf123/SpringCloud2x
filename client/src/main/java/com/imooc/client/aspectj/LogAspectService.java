package com.imooc.client.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspectService {
    @Pointcut(value = "execution(public * com.imooc.client.controller.*.*(..))")
    private void controllerPintCut(){ }
    @Pointcut(value = "execution(public * com.imooc.client.*.*(..))")
    private void controllerPintCut1(){ }

    @Before(value = "controllerPintCut()")
    public void methodBefore(JoinPoint joinPoint){
        log.info(joinPoint.getSignature().getName());
        log.info("前置拦截");
    }

    /**
     * 环绕通知必须有返回值
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around(value = "controllerPintCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        Object result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        log.info("环绕拦截"+result);
        return result;
    }

    @AfterThrowing(value = "controllerPintCut1()",throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint,Exception throwable){
        log.info("异常拦截"+throwable);
    }

    @AfterReturning(value = "controllerPintCut()",returning = "result")
    public void methodAfterReturn(JoinPoint joinPoint,Object result){
        log.info("后置返回拦截"+result);
    }

    @AfterReturning(value = "controllerPintCut1()")
    public void methodAfter(JoinPoint joinPoint){
        log.info("后置拦截");
    }
}
