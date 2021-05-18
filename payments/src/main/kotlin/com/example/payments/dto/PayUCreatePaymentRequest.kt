package com.example.payments.dto

data class PayUCreatePaymentRequest(
    val buyer: Buyer,
    val currencyCode: String,
    val customerIp: String,
    val description: String,
    val merchantPosId: String,
    val notifyUrl: String,
    val products: List<Product>,
    val totalAmount: String
)

data class Buyer(
    val email: String,
    val firstName: String,
    val language: String,
    val lastName: String,
    val phone: String
)

data class Product(
    val name: String,
    val quantity: String,
    val unitPrice: String
)