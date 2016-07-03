package com.meli.api.galaxy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello-test")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return "Hello Walter!";
	}
}
