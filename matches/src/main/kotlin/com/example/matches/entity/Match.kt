package com.example.matches.entity

import java.util.*
import javax.persistence.*

@Entity
data class Match(
    @Id @GeneratedValue
    @Column(name="match_id")
    var id: Long? = null,
    var name: String,
    @OneToMany(mappedBy = "match")
    var allAvailableSeats: MutableList<SeatTicket>,
    var beginDate: Date,
    var durationTime: Int,
    @ManyToOne
    var teamOne: Team,
    @ManyToOne
    var teamTwo: Team,
)