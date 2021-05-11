package com.example.payments.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PayUAuthorizationRequest(
    @JsonProperty("grant_type")
    val grantType: String,
    @JsonProperty("client_id")
    val clientId: String,
    @JsonProperty("client_secret")
    val clientSecret: String
)
