package com.example.atendees

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Atendee(
    @Id
    var pesel: String,
    var startDate: LocalDate,
    var endDate: LocalDate
)
