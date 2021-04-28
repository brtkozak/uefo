package com.example.payments.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : JpaRepository<Ticket, Long>