package com.example.payments.controller

import com.example.payments.dto.CreatePaymentDto
import com.example.payments.dto.Notification
import com.example.payments.dto.Payment
import com.example.payments.repository.PaymentRepository
import com.example.payments.repository.NotificationRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/payments")
class PaymentsController(
    private val paymentRepository: PaymentRepository,
    private val notificationRepository: NotificationRepository
) {

    @GetMapping("/test")
    fun getTest(): String {
        return "payments"
    }

    @GetMapping("/{id}")
    fun getPayment(@PathVariable id: Long): Optional<Payment> {
        return paymentRepository.findById(id)
    }

    @PostMapping("/payment")
    fun createPayment(@RequestBody createPaymentDto: CreatePaymentDto): String {
        // TODO add logic with creating new payment via PayU and so on
        paymentRepository.save(
            Payment(
                name = createPaymentDto.name,
                orderId = createPaymentDto.orderId,
                customerId = createPaymentDto.customerId,
                currencyCode = createPaymentDto.currencyCode
            )
        )
        return "Payment has been created"
    }

    @PostMapping("/notification")
    fun notification(notification: Notification) : String {
        notificationRepository.save(notification)
        return "Notification has been received"
    }

}