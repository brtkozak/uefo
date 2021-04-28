package com.example.atendees

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/atendees")
class AtendeesController() {

    @GetMapping("/test")
    fun getTest() : String {
        return "atendees"
    }

}