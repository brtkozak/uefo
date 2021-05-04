package com.example.matches

import java.util.*
import javax.persistence.*

@Entity
data class Match(
    var name: String,
    var allAvailableSeats: String,
    var beginDate: Date,
    var durationTime: Number,
    var teamOne: Team,
    var teamTwo: Team,
    @Id @GeneratedValue var id: Long? = null
)