package com.imooc.order.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspectService {
    @Pointcut(value = "execution(public * com.imooc.order.web.controller.*.*(..))")
    private void controllerPintCut(){ }
    @Pointcut(value = "execution(public * com.imooc.order.web.*.*(..))")
    private void controllerPintCut1(){ }

    @Before(value = "controllerPintCut()")
    public void methodBefore(JoinPoint joinPoint){
        log.info("前置拦截"+joinPoint.getSignature().getName());
    }

    @Around(value = "controllerPintCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        log.info("环绕拦截"+result);
        return result;
    }

    @AfterThrowing(value = "controllerPintCut()",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Throwable ex)
    {
        log.info("异常拦截"+ex);
    }

    @AfterReturning(value = "controllerPintCut()",returning = "result")
    public void methodAfterReturn(JoinPoint joinPoint,Object result)
    {
        log.info("后置返回拦截"+result);
    }

    @AfterReturning(value = "controllerPintCut()")
    public void methodAfter(JoinPoint joinPoint){
        log.info("后置拦截");
    }


}
