package com.example.matches.service

import com.example.matches.entity.SeatTicket
import com.example.matches.repository.SeatTicketRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SeatTicketService (
    private  val seatTicketRepository: SeatTicketRepository
) {
    fun getSeatTicket(seatTicketId: Long): SeatTicket? {
        return seatTicketRepository.findByIdOrNull(seatTicketId)
    }
}