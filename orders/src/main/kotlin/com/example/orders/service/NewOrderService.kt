package com.example.orders.service;

import com.example.orders.dto.AtendeeRestrictionDto
import com.example.orders.dto.NewTicketDto
import com.example.orders.dto.payment.CreatePaymentRequest
import com.example.orders.dto.payment.CreatePaymentResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDate

@Service
public class NewOrderService {
    companion object {
        const val PAYMENTS_SERVER_URL = "localhost:8085"
        const val ATENDEES_SERVER_URL = "localhost:8082"
        const val MATCHES_SERVER_URL = "localhost:8082"
    }

    private val paymentsWebClient: WebClient = WebClient.builder()
        .baseUrl(PAYMENTS_SERVER_URL)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    private val atendeesWebClient: WebClient = WebClient.builder()
        .baseUrl(ATENDEES_SERVER_URL)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    fun requestOrderPayment(paymentBody: CreatePaymentRequest): CreatePaymentResponse? {
        val request = paymentsWebClient
            .post()
            .uri("/payment")
            .body(BodyInserters.fromValue(paymentBody))
        val response = request
            .exchange()
            .block()
        if (response?.statusCode() != HttpStatus.OK) {
            throw Exception()
        }
        return response.bodyToMono(CreatePaymentResponse::class.java).block()
    }

    fun checkAtendeesRestrictions(tickets: List<NewTicketDto>): List<String> {
        val now = LocalDate.now()

        return tickets
            .mapNotNull { getAtendeeRestriction(it.atendee.pesel) }
            .filter { now.isAfter(it.startDate) && now.isBefore(it.endDate) }
            .map { it.pesel }
    }

    private fun getAtendeeRestriction(atendeePesel: String): AtendeeRestrictionDto? {
        val request = atendeesWebClient
            .get()
            .uri("/atendees/${atendeePesel}")
        val response = request
            .exchange()
            .block()
        if (response?.statusCode() != HttpStatus.OK) {
            throw Exception()
        }
        return response.bodyToMono(AtendeeRestrictionDto::class.java).block()
    }
}
