package com.example.matches.controller

import com.example.matches.entity.SeatTicket
import com.example.matches.service.SeatTicketService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/seats")
class SeatTicketController(private val seatTicketService: SeatTicketService) {

    @GetMapping("/{id}")
    fun getSeatTicketById(@PathVariable id: Long): ResponseEntity<SeatTicket> {
        return try {
            val seatTicket = seatTicketService.getSeatTicket(id)
            if(seatTicket != null)
                ResponseEntity.ok(seatTicket)
            else
                ResponseEntity.notFound().build()
        }
        catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}