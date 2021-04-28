package com.example.matches.data

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class Match(
    var name: String,
    var allAvailableSeats: String,
    var beginDate: Date,
    var durationTime: Number,
    var teamOne: Team,
    var teamTwo: Team,
    @Id @GeneratedValue var id: Long? = null)