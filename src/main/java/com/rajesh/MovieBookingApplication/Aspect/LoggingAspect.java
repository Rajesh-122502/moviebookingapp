package com.rajesh.MovieBookingApplication.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER= LoggerFactory.getLogger(LoggingAspect.class);
    @Before("execution(* com.rajesh.MovieBookingApplication.Service..*(..))")
    public void logMethodCallBefore(JoinPoint joinPoint){
        LOGGER.info("method "+joinPoint.getSignature().toShortString()+ " ra execution start hela");
    }
    @After("execution(* com.rajesh.MovieBookingApplication.Service..*(..))")
    public void logMethodCallAfter(JoinPoint joinPoint){
        LOGGER.info("method "+joinPoint.getSignature().toShortString()+" ra execution end hela");
    }
    @AfterThrowing("execution(* com.rajesh.MovieBookingApplication.Service..*(..))")
    public void logMethodCallAfterError(JoinPoint joinPoint){
        LOGGER.info("ki bala coding karuchu j "+joinPoint.getSignature().toShortString()+ " method re Error asuchi");
    }



    @Before("execution(* com.rajesh.MovieBookingApplication.Controller..*(..))")
    public void controllerLogMethodCallBefore(JoinPoint joinPoint){
        LOGGER.info("method "+joinPoint.getSignature().toShortString()+ " ra execution start hela");
    }
    @After("execution(* com.rajesh.MovieBookingApplication.Controller..*(..))")
    public void ControllerLogMethodCallAfter(JoinPoint joinPoint){
        LOGGER.info("method "+joinPoint.getSignature().toShortString()+ " ra execution end hela");
    }
    @AfterThrowing("execution(* com.rajesh.MovieBookingApplication.Controller..*(..))")
    public void ControllerLogMethodCallAfterError(JoinPoint joinPoint){
        LOGGER.info("ki bala coding karuchu j "+joinPoint.getSignature().toShortString()+ " method re Error asuchi");
    }
}
