package com.example.payments.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Buyer(
    var email: String,
    var phone: String,
    var firstName: String,
    var lastName: String,
    var language: String,
    @Id @GeneratedValue var id: Long? = null
)