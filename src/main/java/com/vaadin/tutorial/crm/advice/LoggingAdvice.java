package com.vaadin.tutorial.crm.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice  {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    // package.className.methodName (set of parameters)
    @Pointcut(value = "execution(* com.vaadin.tutorial.crm.backend.service.*.*(..))")
    public void myPoincut() {

    }

    @Around("myPoincut()")
    public Object applicationLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper ();

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object[] array = joinPoint.getArgs();

        log.info("method invoked " + className + " : " + methodName + "()" + "arguments : "
                + mapper.writeValueAsString(array));

        Object object = joinPoint.proceed();

        log.info(className + " : " + methodName + "()" + "Response : "
                + mapper.writeValueAsString(object));

        return object;
    }

}
