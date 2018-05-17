/**
 * 
 */
package com.aop.aopsamples.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aop.aopsamples.annotation.Time;

/**
 * @author dulip
 *
 */
@RestController
@RequestMapping("/api")
public class AnnotationController {

	@RequestMapping(value="/ann", method = RequestMethod.GET)
	@Time
	public String getDummyResponce() throws InterruptedException {
		Thread.sleep(2000);
		return "Message From -- getDummyResponce";
	}
}
