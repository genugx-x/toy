package com.genug.toy.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/admin")
public class HealthCheckController {
    
    @GetMapping("/")
    public String healthCheck() {
//        String message = "The service is up and running....";
//        return ResponseEntity.ok(message);
        return "The service is up and running....";
    }
}
