package com.example.payments.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Property (
    @Id
    @GeneratedValue
    var id: Long? = null,

    var name: String
)