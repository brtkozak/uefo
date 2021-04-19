package com.example.orders

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class TestController {

    @GetMapping("/test")
    fun getTest() : String {
        return "orders"
    }

}