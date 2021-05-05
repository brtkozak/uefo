package com.example.payments.controller

import com.example.payments.dto.CreatePaymentDto
import com.example.payments.entity.Notification
import com.example.payments.entity.Payment
import com.example.payments.repository.PaymentRepository
import com.example.payments.repository.NotificationRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/payments")
class PaymentsController(
    private val paymentRepository: PaymentRepository
) {

    @GetMapping("/{id}")
    fun getPayment(@PathVariable id: Long): Optional<Payment> {
        return paymentRepository.findById(id)
    }

    @DeleteMapping("/{id}")
    fun deletePayment(@PathVariable id : Long) {
        paymentRepository.deleteById(id)
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

}