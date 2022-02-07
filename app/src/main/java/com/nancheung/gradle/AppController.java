package com.nancheung.gradle;

import api.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gradle")
public class AppController {
    
    @GetMapping
    public Api api(Api api) {
        return api;
    }
}
