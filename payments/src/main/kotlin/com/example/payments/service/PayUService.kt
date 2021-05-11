package com.example.payments.service

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class PayUService(
    private val payUAuthorizationService: PayUAuthorizationService
) {

    private var oauthToken: String? = null
    private val posId = "406445"

    private val webClient: WebClient = WebClient.builder()
        .baseUrl("https://secure.payu.com/pl/standard/user/oauth/authorize")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .build()

    init {
        getToken()
    }

    private fun getToken() {
        payUAuthorizationService.getToken()
            .subscribe {
                if(it.isNotEmpty())
                    oauthToken = it
            }
    }

}