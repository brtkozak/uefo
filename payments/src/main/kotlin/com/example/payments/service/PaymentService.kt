package com.example.payments.service

import com.example.payments.dto.Converter
import com.example.payments.dto.CreatePaymentRequest
import com.example.payments.dto.CreatePaymentResponse
import com.example.payments.dto.PayUStatus
import com.example.payments.entity.Payment
import com.example.payments.repository.PaymentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import javax.persistence.Convert

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val payUService: PayUService
) {

    fun deletePayment(paymentId: Long): Boolean {
        val payment = getPayment(paymentId)
        return if (payment == null)
            false
        else {
            paymentRepository.deleteById(paymentId)
            true
        }
    }

    fun getPayment(paymentId: Long): Payment? {
        return paymentRepository.findByIdOrNull(paymentId)
    }

    fun createPayment(createPaymentRequest: CreatePaymentRequest): Mono<CreatePaymentResponse?> {
        val payUCreatePaymentRequest = Converter.buildPayUCreatePaymentRequest(createPaymentRequest)
        return payUService.createOrder(payUCreatePaymentRequest)
            .map {
                if (it.status.statusCode == PayUStatus.SUCCESS.name) {
                    val payment = Payment.buildFromCreatePaymentDto(createPaymentRequest)
                    paymentRepository.save(payment)
                    CreatePaymentResponse(it.redirectUri)
                } else null
            }
    }
}

