package com.example.demo.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.demo.service.TimeSheetService.*(..))")
    public void pointcutTimeSheetService(){}

    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    public void pointcutService(){}

    @Before(value = "execution(* com.example.demo.service.TimeSheetService.*(..))")
    public void beforeTimeSheetServiceFindById(JoinPoint joinPoint){
        String s = "";
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            s = s + joinPoint.getArgs()[i].getClass() + " " + joinPoint.getArgs()[i] + ", ";
        }
        System.out.println("Before com.example.service.TimeSheetService.getTimeSheet(" + s + ")");
    }

    @Before(value = "execution(* com.example.demo.service.TimeSheetService.*(..))")
    public void beforeTimeSheetService(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Before com.example.service.TimeSheetService " + methodName);
    }

    @After(value = "pointcutTimeSheetService()")
    public void AfterTimeSheetService(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("After com.example.service.TimeSheetService " + methodName);
    }

    @AfterThrowing(value = "pointcutTimeSheetService()", throwing = "ex")
    public void AfterTimeSheetService(JoinPoint joinPoint, Exception e){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("AfterThrowing com.example.service.TimeSheetService " + methodName + e.getClass().getName());
    }

    @Around(value = "pointcutService()")
    public Object AroundTimeSheetService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;
            System.out.println(proceedingJoinPoint.getSignature().getName() + " " + duration + " ms");
        }
    }
}
