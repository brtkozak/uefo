package com.example.orders.dto

import java.time.LocalDate

data class AtendeeRestrictionDto(
    val pesel: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
)
