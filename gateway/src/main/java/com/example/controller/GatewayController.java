package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GatewayController {

    @GetMapping("/test")
    String getTest() {
        return "Hello from Gateway";
    }


    //Test endpoints
    @RequestMapping("/matches")
    String matchesTest() {
        return "matches";
    }

    @RequestMapping("/orders")
    String ordersTest() {
        return "orders";
    }

    @RequestMapping("/payments")
    String paymentsTest() {
        return "payments";
    }


    //Endpoints to forward
    @RequestMapping("/matches/*")
    String matches() {
        return "matches";
    }

    @RequestMapping("/orders/*")
    String orders() {
        return "orders";
    }

    @RequestMapping("/payments/*")
    String payments() {
        return "payments";
    }

}
