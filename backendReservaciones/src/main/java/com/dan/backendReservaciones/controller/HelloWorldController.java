package com.dan.backendReservaciones.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dan.backendReservaciones.entity.HelloWorld;

@RestController
@RequestMapping("api/greeting")
public class HelloWorldController {
	private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();
    
    @GetMapping("/hello-world")
    public HelloWorld helloWorld(@RequestParam(value = "name", defaultValue = "World") String name){
        return new HelloWorld(counter.incrementAndGet(), String.format(template, name));
    }
}
