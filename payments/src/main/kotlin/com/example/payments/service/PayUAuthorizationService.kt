package com.example.payments.service

import com.example.payments.dto.PayUAuthorizationRequest
import com.example.payments.dto.PayUAuthorizationResponse
import com.google.gson.Gson
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class PayUAuthorizationService {

    private val clientId = "145227"
    private val clientSecret = "12f071174cb7eb79d4aac5bc2f07563f"
    private val grantType = "client_credentials"
    private val oauthToken: String? = null

    private val webClient: WebClient = WebClient.builder()
        .baseUrl("https://secure.payu.com/pl/standard/user/oauth/authorize")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .build()

    fun getToken(): Mono<String> {
        val formData = LinkedMultiValueMap<String, String>().apply {
            add("grant_type", grantType)
            add("client_id", clientId)
            add("client_secret", clientSecret)
        }

        return webClient
            .post()
            .uri("")
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(PayUAuthorizationResponse::class.java)
            .map {
                it.accessToken
            }.onErrorReturn("")
    }

}