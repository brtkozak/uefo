package com.example.orders.dto

import java.util.*

data class MatchDto(
    val name: String,
    val allAvailableSeats: MutableList<TicketSeatDto>,
    val beginDate: Date,
    val durationTime: Int,
    val teamOne: TeamDto,
    val teamTwo: TeamDto,
)

data class TicketSeatDto(
    val id: Long,
    val sector: String,
    val seatNumber: String,
    val seatPrice: Double,
)
