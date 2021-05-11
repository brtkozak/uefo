package com.example.payments.service

import com.example.payments.dto.CreatePaymentDto
import com.example.payments.entity.Payment
import com.example.payments.repository.PaymentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    fun deletePayment(paymentId : Long): Boolean {
        val payment = getPayment(paymentId)
        return if(payment == null)
            false
        else {
            paymentRepository.deleteById(paymentId)
            true
        }
    }

    fun getPayment(paymentId: Long): Payment? {
        return paymentRepository.findByIdOrNull(paymentId)
    }

    fun createPayment(createPaymentDto: CreatePaymentDto): Payment {
        // TODO add logic with PayU and so on
        val payment = Payment.buildFromCreatePaymentDto(createPaymentDto)
        return paymentRepository.save(payment)
    }

}