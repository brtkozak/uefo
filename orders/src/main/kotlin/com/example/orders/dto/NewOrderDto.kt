package com.example.orders.dto

data class NewOrderDto (
    val userId: Long,
    val matchId: Long,
    val tickets: List<NewTicketDto>
)
