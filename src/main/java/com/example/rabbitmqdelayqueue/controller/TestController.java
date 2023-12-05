package com.example.rabbitmqdelayqueue.controller;


import com.example.rabbitmqdelayqueue.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Thread.sleep;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/test1")
    public String test1() throws InterruptedException {
        testService.sendDelayedMessage(5);
        for (int i = 1; i < 5; i++) {
            sleep(1000);
            log.info("{}", i);
        }
        return "OK";
    }
}
