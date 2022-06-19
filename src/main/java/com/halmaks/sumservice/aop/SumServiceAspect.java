package com.halmaks.sumservice.aop;

import com.halmaks.sumservice.models.Response;
import com.halmaks.sumservice.models.SumResponse;
import com.halmaks.sumservice.services.RequestDescriptionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Aspect
public class SumServiceAspect {

    private static final String VALUE = "@annotation(com.halmaks.sumservice.annotation.Marker)";

    private final RequestDescriptionService descriptionService;

    @Before(value = VALUE)
    public void before(JoinPoint joinPoint) throws Throwable {
        final String args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        LogFactory.getLog(SumServiceAspect.class)
                .info("monitor.before, class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() +
                        ", method: " + joinPoint.getSignature().getName() + ", kind: " + joinPoint.getKind() +
                        ", args: " + args);
    }

    @After(value = VALUE)
    public void after(JoinPoint joinPoint) throws Throwable {
        final String args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        LogFactory.getLog(SumServiceAspect.class)
                .info("monitor.after, class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() +
                        ", method: " + joinPoint.getSignature().getName() + ", kind: " + joinPoint.getKind() +
                        ", args: " + args);
    }

    @AfterThrowing(value = VALUE)
    public void afterThrowing(JoinPoint joinPoint) throws Throwable {
        final String args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        LogFactory.getLog(SumServiceAspect.class)
                .info("monitor.afterThrowing, class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() +
                        ", method: " + joinPoint.getSignature().getName() + ", kind: " + joinPoint.getKind() +
                        ", args: " + args);
    }






    @Around(value = VALUE)
    public Object afterThrowing(ProceedingJoinPoint joinPoint) throws Throwable {
        final String args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        LogFactory.getLog(SumServiceAspect.class)
                .info("monitor.around, class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() +
                        ", method: " + joinPoint.getSignature().getName() + ", kind: " + joinPoint.getKind() +
                        ", args: " + args);
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            return new SumResponse(
                    1000,
                    new Response(0, descriptionService.getDescriptionByCode(0)));
        }
    }
}
