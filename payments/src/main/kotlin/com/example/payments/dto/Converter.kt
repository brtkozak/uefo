package com.example.payments.dto

import com.example.payments.entity.Ticket
import com.example.payments.service.PayUService

class Converter {

    companion object {

        fun buildPayUCreatePaymentRequest(createPaymentRequest: CreatePaymentRequest): PayUCreatePaymentRequest {
            val totalAmount = createPaymentRequest.tickets.sumByDouble { it.unitPrice.toDouble() * 100 * it.quantity.toInt() }.toInt()
            return PayUCreatePaymentRequest(
                notifyUrl = "https://your.eshop.com/notify",
                customerIp = "127.0.0.1",
                merchantPosId = PayUService.posId,
                description = "whatever",
                currencyCode = createPaymentRequest.currencyCode,
                totalAmount = totalAmount.toString(),
                buyer = Buyer(
                    email = "customer@gmail.com",
                    phone = "123456789",
                    firstName = "Name",
                    lastName = "LastName",
                    language = "pl"

                ),
                products = createPaymentRequest.tickets.map { buildProductFromTicket(it) }
            )
        }

        private fun buildProductFromTicket(ticket : Ticket) : Product {
            return Product(
                name = ticket.name,
                quantity = ticket.quantity.toString(),
                unitPrice = (ticket.unitPrice.toDouble() * 100).toInt().toString()
            )
        }
    }

}