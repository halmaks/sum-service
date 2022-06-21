package com.halmaks.sumservice.aop;

import com.halmaks.sumservice.models.Response;
import com.halmaks.sumservice.models.SumResponse;
import com.halmaks.sumservice.services.RequestDescriptionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Aspect
public class SumServiceAspect {

    private static final String VALUE = "@annotation(com.halmaks.sumservice.annotation.Marker)";

    private final RequestDescriptionService descriptionService;

    @Around(value = VALUE)
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        final String args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        LogFactory.getLog(SumServiceAspect.class)
                .info("monitor.afterThrowing, class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() +
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
