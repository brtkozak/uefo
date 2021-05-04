package com.example.orders

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class OrdersBuyer(
    var email: String,
    var phone: String,
    var firstName: String,
    var lastName: String,
    var language: String,
    @Id @GeneratedValue var id: Long? = null
)