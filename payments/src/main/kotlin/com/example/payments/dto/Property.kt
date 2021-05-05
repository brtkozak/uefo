package com.example.payments.dto

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
data class Property (
    @Id
    @GeneratedValue
    var id: Long? = null,
    var name: String,
    var value: String
)