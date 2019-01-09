package com.wishlist.logging.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingConfiguration {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.wishlist.controller.*.*(..))")
	public void before(JoinPoint joinPoint) {
		LOG.info("Before Logging JoinPoint");
		LOG.info("Allowed execution for {}", joinPoint);
	}

	@AfterReturning(value = "execution(* com.aop.demo.service.*.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		LOG.info("{} returned with value {}", joinPoint, result);
	}

	@After(value = "execution(* com.aop.demo.service.*.*(..))")
	public void after(JoinPoint joinPoint) {
		LOG.info("After Logging JoinPoint");
		LOG.info("After execution of {}", joinPoint);
	}

}
