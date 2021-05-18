package com.example.orders.service;

import com.example.orders.dto.payment.CreatePaymentResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
public class NewOrderService {
    companion object {
        const val PAYMENTS_SERVER_URL = "localhost:8085"
    }

    private val paymentsWebClient: WebClient = WebClient.builder()
        .baseUrl(PAYMENTS_SERVER_URL)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    fun requestOrderPayment(): CreatePaymentResponse? {
        val request = paymentsWebClient
            .post()
            .uri("/payment")
        val response = request
            .exchange()
            .block()
        if (response?.statusCode() != HttpStatus.OK) {
            throw Exception()
        }
        return response.bodyToMono(CreatePaymentResponse::class.java).block()
    }
}
