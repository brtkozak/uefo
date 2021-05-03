package com.example.atendees

import Atendee
import com.example.atendees.data.AtendeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/atendees")
class AtendeesController() {

    @Autowired
    lateinit var atendeeRepo: AtendeeRepository

    @GetMapping("/test")
    fun getTest() : String {
        return "atendees"
    }


    @GetMapping("/{id}")
    fun getAtendee(@PathVariable id: Long): Optional<Atendee> {
        return atendeeRepo.findById(id)
    }

    @PostMapping("/save")
    fun save(@RequestBody atendee: Atendee): String {
        atendeeRepo.save(atendee)
        return "Successfully saved an attendee"
    }

    @GetMapping("/all")
    fun getAll(): List<Atendee>{
        return atendeeRepo.findAll()
    }
}