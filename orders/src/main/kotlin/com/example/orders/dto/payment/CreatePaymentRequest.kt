package com.example.orders.dto.payment

data class CreatePaymentRequest(
    var name: String,
    var orderId: Long,
    var customerId: Long,
    var currencyCode: String,
    //TODO are tickets useful here?
    var tickets: Any
)
