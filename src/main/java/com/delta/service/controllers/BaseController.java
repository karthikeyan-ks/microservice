package com.delta.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BaseController {
    @GetMapping("/health")
    public String getHealthCheck(){
        return "pod is healthy";
    }
}
