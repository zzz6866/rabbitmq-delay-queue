package com.example.rabbitmqdelayqueue.controller;


import com.example.rabbitmqdelayqueue.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/test1")
    public String test1() {
        testService.sendDelayedMessage();
        return "OK";
    }
}
