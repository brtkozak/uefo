package com.example.matches

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/matches")
class TestController {

    @GetMapping("/test")
    fun getTest() : String {
        return "matches"
    }

}