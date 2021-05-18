package com.example.payments.entity

import com.example.payments.dto.CreatePaymentRequest
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Payment(
    var name: String,
    var orderId: Long,
    var customerId: Long,
    var currencyCode: String,
    @Id @GeneratedValue var id: Long? = null
) {

    companion object {
        fun buildFromCreatePaymentDto(createPaymentRequest: CreatePaymentRequest): Payment {
            return  Payment(
                name = createPaymentRequest.name,
                orderId = createPaymentRequest.orderId,
                customerId = createPaymentRequest.customerId,
                currencyCode = createPaymentRequest.currencyCode
            )
        }
    }

}