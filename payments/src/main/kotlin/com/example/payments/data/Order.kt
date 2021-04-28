package com.example.payments.data

import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id

class Order (
    var extOrderId: Long,
    var orderCreateDate: Date,
    var notifyUrl: String,
    var customerId: Long,
    var merchantPosId: Long,
    var description: String,
    var currencyCode: String,
    var totalAmount: Number,
    var buyer: Buyer,
    var payMethod: String,
    var products: List<Product>,
    var status: String,
    @Id
    @GeneratedValue
    var id: Long? = null
)