package com.example.payments.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Notification(
    var order: Order,
    var localReceiptDateTime: String,
    var properties: Property,
    @Id @GeneratedValue var id: Long? = null
)