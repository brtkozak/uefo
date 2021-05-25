package com.example.orders.dto.payment

data class CreatePaymentRequest(
    val name: String,
    val orderId: Long,
    val customerId: Long,
    val currencyCode: String,
    val tickets: List<TicketForPaymentDto>
)

data class TicketForPaymentDto (
    val name: String,
    val unitPrice: Number,
    val quantity: Number,
    val id: Long? = null
)
