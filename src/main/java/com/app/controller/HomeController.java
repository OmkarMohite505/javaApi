package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
	
	@GetMapping("/health")
	public ResponseEntity<?> health() {
		return ResponseEntity.status(HttpStatus.OK).body("healthy");
	}
	
	@RequestMapping("/ui")
    public String forward() {
        // Forward requests to the React app
        return "forward:/ui/index.html";
    }
}
