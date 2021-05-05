package com.example.orders.dto

import com.example.orders.Ticket

data class OrderDetailsDto (
    val id: Long,
    val userId: Long,
    val matchId: Long,
    val tickets: List<Ticket>
)
