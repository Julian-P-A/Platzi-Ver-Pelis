package com.platzi.ver_pelis_plus.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.platzi.ver_pelis_plus.services.interfaces.VerPelisAiService;

@RestController
public class HelloController {	
	private final String platform;
    private final VerPelisAiService aiService;
    
    
    public HelloController(@Value("${spring.application.name}") String platform, VerPelisAiService aiService) {
    	this.platform = platform;
        this.aiService = aiService;
    }

    @GetMapping("/hello")
    public String hello() {
        return this.aiService.generateGreeting(platform);
    }
}
