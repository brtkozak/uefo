package com.example.payments

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table


@Entity
data class Payment(
    var name: String,
    var orderId: Long,
    var customerId: Long,
    var currencyCode: String,
    var tickets: String, //todo: define the relationship
    @Id @GeneratedValue var id: Long? = null
)