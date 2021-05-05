package com.example.payments.dto

import com.example.payments.entity.Ticket

data class CreatePaymentDto(
    var name: String,
    var orderId: Long,
    var customerId: Long,
    var currencyCode: String,
    var tickets: List<Ticket>
)