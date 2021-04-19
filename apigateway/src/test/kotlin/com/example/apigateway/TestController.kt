package com.example.apigateway

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api-gateway")
class TestController {

    @GetMapping("/test")
    fun getTest() : String {
        return "api-gateway"
    }

}