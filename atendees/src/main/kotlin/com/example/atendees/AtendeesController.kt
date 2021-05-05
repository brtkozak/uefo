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


    @GetMapping("/{id}")
    fun getAtendee(@PathVariable id: Long): Optional<Atendee> {
        return atendeeRepo.findById(id)
    }

    @PostMapping("/save")
    fun save(@RequestBody atendee: Atendee): Atendee {
        return atendeeRepo.save(atendee)
    }

    @GetMapping("/all")
    fun getAll(): List<Atendee> {
        return atendeeRepo.findAll()
    }

    @DeleteMapping("/{id}")
    fun deleteAtendee(@PathVariable id: Long) {
        atendeeRepo.deleteById(id)
    }
}