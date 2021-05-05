package com.example.payments.dto

data class CreatePaymentDto(
    var name: String,
    var orderId: Long,
    var customerId: Long,
    var currencyCode: String,
    var tickets: List<Ticket>
)