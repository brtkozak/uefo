package com.example.payments

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Notification(
    var order: String,
    var localReceiptDateTime: String,
    var properties: String,
    @Id @GeneratedValue var id: Long? = null
)