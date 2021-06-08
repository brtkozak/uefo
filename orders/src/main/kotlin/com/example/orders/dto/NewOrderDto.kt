package com.example.orders.dto

import com.example.orders.Order
import com.example.orders.dto.payment.CreatePaymentResponse

data class NewOrderDto (
    val order: Order,
    val paymentReq: CreatePaymentResponse
)
