package com.example.orders.dto

data class NewTicketDto (
    val atendee: Atendee,
    val seatId: Long,
)

data class Atendee (
    val name: String,
    val surname: String,
    val pesel: String
)
