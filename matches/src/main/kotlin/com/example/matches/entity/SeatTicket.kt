package com.example.matches.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class SeatTicket(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var sector: String,
    var seatNumber: String,
    var seatPrice: Double,
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="match_fk")
    var match: Match
)