package com.example.orders.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Seat(
    var sector: String,
    var seatNumber: Number,
    @Id @GeneratedValue var id: Long? = null)
