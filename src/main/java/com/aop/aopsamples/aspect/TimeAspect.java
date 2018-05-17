/**
 * 
 */
package com.aop.aopsamples.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
public class TimeAspect {

	private static Logger logger = LoggerFactory.getLogger(TimeAspect.class);

	@Around("@annotation(com.aop.aopsamples.annotation.Time)")
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
		}
		return proceed;
	}

}
