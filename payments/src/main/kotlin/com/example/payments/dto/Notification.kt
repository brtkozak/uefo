package com.example.payments.dto

import javax.persistence.*

@Entity
class Notification(
    var order: String,
    var localReceiptDateTime: String,
    @OneToMany
    var properties: MutableList<Property>,
    @Id @GeneratedValue var id: Long? = null
)