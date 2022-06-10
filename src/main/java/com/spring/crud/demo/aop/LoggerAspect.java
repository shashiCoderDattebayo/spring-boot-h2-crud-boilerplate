package com.spring.crud.demo.aop;


import java.util.Objects;

import com.spring.crud.demo.models.Booking;
import com.spring.crud.demo.models.Branch;
import com.spring.crud.demo.models.Price;
import com.spring.crud.demo.models.Vehicle;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Before("@annotation(com.spring.crud.demo.annotation.LogObjectBefore)")
    public void logSuperHeroBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Booking) {
                Booking booking = (Booking) arg;
                log.info("******* Booking before :: {}", booking);
            } else if (arg instanceof Branch) {
                Branch branch = (Branch) arg;
                log.info("******* Branch before :: {}", branch);
            } else if (arg instanceof Price) {
                Price price = (Price) arg;
                log.info("******* Price before :: {}", price);
            } else if (arg instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) arg;
                log.info("******* Vehicle before :: {}", vehicle);
            }
        }
    }

    @AfterReturning(value = "@annotation(com.spring.crud.demo.annotation.LogObjectAfter)", returning = "result")
    public void logSuperHeroAfter(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();
        if (Objects.nonNull(result)) {
            if (result instanceof ResponseEntity) {
                ResponseEntity responseEntity = (ResponseEntity) result;

                if (responseEntity.getStatusCode().value() == 200) {
                    log.info("******* Returning object :: {}", responseEntity.getBody());
                } else {
                    log.error("Something went wrong while logging...!");
                }
            }
        }
    }
}
