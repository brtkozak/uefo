package com.example.payments.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Ticket(
    var name: String,
    var unitPrice: Number,
    var quantity: Number,
    @Id var id: Long
)
