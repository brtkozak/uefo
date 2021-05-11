package com.example.payments.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PayUAuthorizationResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("expires_in")
    val expiresIn: Long,
    @JsonProperty("grant_type")
    val grantType: String
)
