package com.example.payments.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
data class Ticket(
    var name: String,
    var unitPrice: Number,
    var quantity: Number,
    @Id @GeneratedValue var id: Long? = null
)