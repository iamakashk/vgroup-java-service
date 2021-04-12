package com.vgroup.assesment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRoleController {
	
	@RequestMapping("/")
	public String home() {
		return ("<h1>Welcome Home</h1>");
	}
	
	@GetMapping("/user")
	public String user() {
		return ("<h1>Welcome user</h1>");
	}
	
	@PostMapping("/admin")
	public String admin() {
		return ("<h1>Welcome admin</h1>");
	}

}
