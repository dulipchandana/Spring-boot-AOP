/**
 * 
 */
package com.aop.aopsamples.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author dulip
 *
 */
@RestController
@RequestMapping("/api")
public class FrontController {

	@RequestMapping(value="/test", method = RequestMethod.GET)
	//@LogExecution
	public String message() throws InterruptedException {
		Thread.sleep(2000);
		return "Message From -- test";
	}

}
