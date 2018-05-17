/**
 * 
 */
package com.aop.aopsamples.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dulip
 *
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	/*
	 * //@Around("execution(@com.aop.annotation.LogExecution) && @annotation(ann)")
	 * 
	 * @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(public * *(..))"
	 * ) public void LogExecutionTime(ProceedingJoinPoint joinPoint) throws
	 * Throwable {
	 * 
	 * System.out.println(
	 * "------------------------------------------------------------------------------"
	 * ); System.out.println(
	 * "------------------------------------------------------------------------------"
	 * ); long start = System.currentTimeMillis();
	 * 
	 * Object proceed = joinPoint.proceed();
	 * 
	 * long executionTime = System.currentTimeMillis() - start;
	 * 
	 * System.out.println(joinPoint.getSignature() + " executed in " + executionTime
	 * + "ms"); return proceed; }
	 */

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(public * *(..))")
	public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		Object proceed;

		try {
			long start = System.currentTimeMillis();

			proceed = proceedingJoinPoint.proceed();

			long executionTime = System.currentTimeMillis() - start;

			logger.info("{} {} method {} {} ms", proceedingJoinPoint.getSignature(), request.getMethod(),
					request.getRequestURI(), executionTime);

		} catch (Throwable throwable) {
			throw throwable;
		} finally {

			logger.info("{} {} from {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());

		}

		return proceed;
	}

}
