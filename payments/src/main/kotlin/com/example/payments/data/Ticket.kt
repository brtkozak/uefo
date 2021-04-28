package com.example.payments.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class Ticket(
    var name: String,
    var unitPrice: Number,
    var quantity: Number,
    @Id @GeneratedValue var id: Long? = null
)