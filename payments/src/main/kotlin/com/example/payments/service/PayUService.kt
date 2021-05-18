package com.example.payments.service

import com.example.payments.dto.*
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class PayUService(
    private val payUAuthorizationService: PayUAuthorizationService
) {

    companion object {
        val posId = "406445"
    }

    private var oauthToken: String? = null

    private val webClient: WebClient = WebClient.builder()
        .baseUrl("https://secure.snd.payu.com/api/v2_1")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    init {
        getToken()
    }

    private fun getToken() {
        payUAuthorizationService.getToken()
            .subscribe {
                if (it.isNotEmpty())
                    oauthToken = it
            }
    }

    fun createOrder(payUCreatePaymentRequest: PayUCreatePaymentRequest): Mono<PayUCreatePaymentResponse> {
        return webClient
            .post()
            .uri("/orders")
            .header("Authorization", "Bearer $oauthToken")
            .body(Mono.just(payUCreatePaymentRequest), PayUCreatePaymentRequest::class.java)
            .retrieve()
            .bodyToMono(PayUCreatePaymentResponse::class.java)
    }

    private fun getMockPayment(): PayUCreatePaymentRequest {
        return PayUCreatePaymentRequest(
            notifyUrl = "https://your.eshop.com/notify",
            customerIp = "127.0.0.1",
            merchantPosId = posId,
            description = "whatever",
            currencyCode = "PLN",
            totalAmount = "100000",
            buyer = Buyer(
                email = "email@gmail.com",
                phone = "123456789",
                firstName = "name",
                lastName = "lastName",
                language = "pl"

            ),
            products = listOf(
                Product(
                    name = "product name",
                    unitPrice = "50000",
                    quantity = "2"
                )
            )
        )
    }

}