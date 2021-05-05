package com.example.payments.repository

import com.example.payments.entity.PaymentOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentOrderRepository : JpaRepository<PaymentOrder, Long>