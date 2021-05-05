package com.example.payments.repository

import com.example.payments.dto.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : JpaRepository<Ticket, Long>