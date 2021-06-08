package com.example.orders.dto

data class NewOrderFormDataDto (
    val userId: Long,
    val matchId: Long,
    val tickets: List<NewTicketDto>
)
