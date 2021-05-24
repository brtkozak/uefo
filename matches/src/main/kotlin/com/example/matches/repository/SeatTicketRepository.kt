package com.example.matches.repository

import com.example.matches.entity.SeatTicket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SeatTicketRepository : JpaRepository<SeatTicket, Long>