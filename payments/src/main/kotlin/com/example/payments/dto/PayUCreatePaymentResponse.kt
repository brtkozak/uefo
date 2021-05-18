package com.example.payments.dto

data class PayUCreatePaymentResponse(
    val orderId: String,
    val redirectUri: String,
    val status: Status
)

data class Status(
    val statusCode: String
)

enum class PayUStatus {
    SUCCESS
}