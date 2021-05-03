package com.example.matches.data

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Table
@Entity
data class Match(
    var name: String,
    var allAvailableSeats: String,
    var beginDate: Date,
    var durationTime: Number,
    var teamOne: String,
    var teamTwo: String,
    @Id @GeneratedValue var id: Long? = null
)