package com.example.payments.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class Payment(
    var name: String,
    var orderId: Long,
    var customerId: Long,
    var currencyCode: String,
    var tickets: List<Ticket>, //todo: define the relationship
    @Id @GeneratedValue var id: Long? = null
)