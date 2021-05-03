package com.example.orders.data

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Order (
    var extOrderId: Long,
    var orderCreateDate: Date,
    var notifyUrl: String,
    var customerId: Long,
    var merchantPosId: Long,
    var description: String,
    var currencyCode: String,
    var totalAmount: Number,
    var buyer: String,
    var payMethod: String,
    var products: String,
    var status: String,
    @Id
    @GeneratedValue
    var id: Long? = null
)