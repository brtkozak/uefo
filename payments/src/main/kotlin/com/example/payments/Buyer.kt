package com.example.payments

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
data class Buyer(
    var email: String,
    var phone: String,
    var firstName: String,
    var lastName: String,
    var language: String,
    @Id @GeneratedValue var id: Long? = null
)