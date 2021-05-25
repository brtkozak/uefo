package com.example.atendees

import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/atendees")
class AtendeesController(private val atendeeRepo: AtendeeRepository) {

    @GetMapping("/test")
    fun getTest(): String {
        return "atendees"
    }

    @GetMapping("/byPesel/{pesel}")
    fun getAtendee(@PathVariable pesel: String): Optional<Atendee> {
        return atendeeRepo.findByPesel(pesel)
    }

    @PostMapping("/save")
    fun save(@RequestBody atendee: Atendee): Atendee {
        return atendeeRepo.save(atendee)
    }

    @GetMapping("/all")
    fun getAll(): List<Atendee> {
        return atendeeRepo.findAll()
    }
}
