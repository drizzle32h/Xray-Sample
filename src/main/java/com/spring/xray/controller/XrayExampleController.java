package com.spring.xray.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.xray.service.XrayExampleService;

@RestController
public class XrayExampleController {
	private static final Logger LOG = LoggerFactory.getLogger(XrayExampleController.class);
	@Resource(name = "xrayExampleService")
	XrayExampleService xrayExampleService;

	@RequestMapping(value = "/xraydemo")
	public String helloWorld() {
		String response = "Hello user ! " + new Date();
		String rtn = xrayExampleService.xrayDemoServiceMethod(response);
		return rtn;
	}

	@RequestMapping(value = "/xray")
	public String helloWorld1() {
		String rtn = xrayExampleService.xrayServiceMethod();
		return rtn;
	}

	@RequestMapping(value = "/exception")
	public String exception() {
		String rsp = "";
		try {
			int i = 1 / 0;
			// should get exception
		} catch (Exception e) {
			//e.printStackTrace();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			rsp = sStackTrace;
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		return rsp;
	}
}