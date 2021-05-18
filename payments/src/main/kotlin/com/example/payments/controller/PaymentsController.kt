package com.example.payments.controller

import com.example.payments.dto.CreatePaymentRequest
import com.example.payments.dto.CreatePaymentResponse
import com.example.payments.entity.Payment
import com.example.payments.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/payments")
class PaymentsController(
    private val paymentService: PaymentService
) {

    @GetMapping("/{id}")
    fun getPayment(@PathVariable id: Long): ResponseEntity<Payment> {
        return try {
            val payment = paymentService.getPayment(id)
            if (payment != null)
                ResponseEntity.ok(payment)
            else
                ResponseEntity.notFound().build()
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePayment(@PathVariable id: Long): ResponseEntity<Boolean> {
        return try {
            val result = paymentService.deletePayment(id)
            if (result)
                ResponseEntity.ok(true)
            else
                ResponseEntity.notFound().build()
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/payment")
    fun createPayment(@RequestBody createPaymentRequest: CreatePaymentRequest): Mono<ResponseEntity<CreatePaymentResponse>> {
        return try {
            paymentService.createPayment(createPaymentRequest).map {
                if (it != null)
                    ResponseEntity.ok(it)
                else ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            return Mono.just(ResponseEntity.notFound().build())
        }
    }

}