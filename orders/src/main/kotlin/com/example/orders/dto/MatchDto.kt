package com.example.orders.dto

import java.util.*

data class MatchDto(
    var name: String,
    var allAvailableSeats: Int,
    var beginDate: Date,
    var durationTime: Int,
    var teamOne: TeamDto,
    var teamTwo: TeamDto,
)
