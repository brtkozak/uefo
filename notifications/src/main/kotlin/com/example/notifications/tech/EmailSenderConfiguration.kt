package com.example.notifications.tech

data class EmailSenderConfiguration(
    val host: String,
    val port: String,
    val username: String,
    val password: String,
    val addressFrom: String,
    val messageContentType: String
)
