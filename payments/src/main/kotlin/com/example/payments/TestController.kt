package com.example.payments

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payments")
class TestController {

    @GetMapping("/test")
    fun getTest() : String {
        return "payments"
    }

}