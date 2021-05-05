package com.example.matches

import java.util.*
import javax.persistence.*

@Entity
data class Match(
    var name: String,
    var allAvailableSeats: Int,
    var beginDate: Date,
    var durationTime: Int,
    @ManyToOne
    var teamOne: Team,
    @ManyToOne
    var teamTwo: Team,
    @Id @GeneratedValue var id: Long? = null
)