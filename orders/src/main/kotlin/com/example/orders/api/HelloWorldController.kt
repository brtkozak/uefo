package com.example.orders.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping(HelloWorldController.controllerPath)
class HelloWorldController {
    companion object {
        const val controllerPath = "api/hello-world"
    }

    @GetMapping
    fun getHelloWorld(): String {
        return "Hello world, " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }
}
