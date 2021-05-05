package com.example.payments.entity

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
    @Id @GeneratedValue var id: Long? = null
)