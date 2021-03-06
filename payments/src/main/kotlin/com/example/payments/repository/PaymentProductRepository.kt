package com.example.payments.repository

import com.example.payments.entity.PaymentProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentProductRepository : JpaRepository<PaymentProduct, Long>