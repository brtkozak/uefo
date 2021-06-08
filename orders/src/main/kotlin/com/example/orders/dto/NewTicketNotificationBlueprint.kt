package com.example.orders.dto

data class NewTicketNotificationBlueprint (
    val emailAddress: String,
    val language: String,
    val person: Person,
    val match: Match,
    val seats: List<Seat>
)

data class Person (
    val fullName: String
)

data class Match (
    val name: String,
    val date: String
)

data class Seat (
    val sector: String,
    val seat: String
)
